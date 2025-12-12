import axios from 'axios';
import type {Vehicle} from '@/lib/dto.tsx';

const API_BASE_URL = 'https://helios.cs.ifmo.ru:26367/api/v1/vehicles';

const axiosInstance = axios.create({
    baseURL: API_BASE_URL,
    timeout: 10000,
});

export interface VehicleResponse {
    content: Vehicle[];
    number: number;
    size: number;
    totalElements: number;
    totalPages: number;
}

export interface VehicleFilterDto {
    name?: string;
    coordinatesX?: number;
    coordinatesY?: number;
    enginePower?: number;
    numberOfWheels?: number;
    distanceTravelled?: number;
    fuelType?: string;
    sort?: string;
}

export const vehicleService = {
    getVehicles: async (
        page: number = 0,
        size: number = 20,
        filter?: VehicleFilterDto,
        sort?: string
    ): Promise<VehicleResponse> => {
        const params: any = {page, size};
        const paramsForSearch: any = {page, size};
        if (filter) {
            if (filter.name) params.name = filter.name;
            if (filter.coordinatesX !== undefined) params.coordinatesX = filter.coordinatesX;
            if (filter.coordinatesY !== undefined) params.coordinatesY = filter.coordinatesY;
            if (filter.enginePower) params.enginePower = filter.enginePower;
            if (filter.numberOfWheels) params.numberOfWheels = filter.numberOfWheels;
            if (filter.distanceTravelled) params.distanceTravelled = filter.distanceTravelled;
            if (filter.fuelType) params.fuelType = filter.fuelType;
        }

        if (sort) params.sort = sort;
        if (filter?.fuelType != null) {
            const axiosInstanceForSecondService = axios.create(
                {
                    baseURL: 'https://helios.cs.ifmo.ru:26368/api/v1/shop',
                    timeout: 10000,
                }
            )
            const response = await axiosInstanceForSecondService.get<VehicleResponse>(`/search/by-type/${filter.fuelType}`);
            return response.data;
        } else {
            const response = await axiosInstance.get<VehicleResponse>('', paramsForSearch);
            return response.data;
        }
    },

    createVehicle: async (vehicle: Omit<Vehicle, 'id'>): Promise<Vehicle> => {
        const response = await axiosInstance.post<Vehicle>('', vehicle);
        return response.data;
    },

    updateVehicle: async (id: number, vehicle: Partial<Vehicle>): Promise<Vehicle> => {
        const response = await axiosInstance.put<Vehicle>(`/${id}`, vehicle);
        return response.data;
    },

    deleteVehicle: async (id: number): Promise<void> => {
        await axiosInstance.delete(`/${id}`);
    },

    getVehicleById: async (id: number): Promise<Vehicle> => {
        const response = await axiosInstance.get<Vehicle>(`/${id}`);
        return response.data;
    },

    searchByNameContains: async (name: string): Promise<Vehicle[]> => {
        const response = await axiosInstance.get<Vehicle[]>('/search/name-contains', {
            params: {name},
        });
        return response.data;
    },

    searchByNameStartsWith: async (name: string): Promise<Vehicle[]> => {
        const response = await axiosInstance.get<Vehicle[]>('/search/name-starts-with', {
            params: {name},
        });
        return response.data;
    },

    searchByFuelTypeGreaterThan: async (fuelType: string): Promise<Vehicle[]> => {
        const response = await axiosInstance.get<Vehicle[]>('/search/fuel-type-greater-than', {
            params: {fuelType},
        });
        return response.data;
    },
};
