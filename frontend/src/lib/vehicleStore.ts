import { create } from 'zustand';
import type { Vehicle } from '@/lib/dto.tsx';
import {type VehicleFilterDto, vehicleService} from "@/lib/vehicleService.ts";

interface PaginationInfo {
    currentPage: number;
    pageSize: number;
    totalElements: number;
    totalPages: number;
}

interface VehicleState {
    vehicles: Vehicle[];
    pagination: PaginationInfo;
    loading: boolean;
    error: string | null;
    filter: VehicleFilterDto | null;
    sortBy: string | null;
    fetchVehicles: (page: number, pageSize: number, filter?: VehicleFilterDto, sort?: string) => Promise<void>;
    createVehicle: (vehicle: Omit<Vehicle, 'id'>) => Promise<void>;
    updateVehicle: (id: number, vehicle: Partial<Vehicle>) => Promise<void>;
    deleteVehicle: (id: number) => Promise<void>;
    setFilter: (filter: VehicleFilterDto | null) => void;
    setSortBy: (sort: string | null) => void;
    resetFilter: () => void;
}

export const useVehicleStore = create<VehicleState>((set, get) => ({
    vehicles: [],
    pagination: {
        currentPage: 0,
        pageSize: 20,
        totalElements: 0,
        totalPages: 0,
    },
    loading: false,
    error: null,
    filter: null,
    sortBy: null,

    fetchVehicles: async (page: number, pageSize: number, filter?: VehicleFilterDto, sort?: string) => {
        set({ loading: true, error: null });
        try {
            const data = await vehicleService.getVehicles(page, pageSize, filter, sort);
            set({
                vehicles: data.content || [],
                pagination: {
                    currentPage: data.number || 0,
                    pageSize: data.size || 20,
                    totalElements: data.totalElements || 0,
                    totalPages: data.totalPages || 0,
                },
                loading: false,
            });

        } catch (error) {
            set({
                error: error instanceof Error ? error.message : 'Неизвестная ошибка',
                loading: false,
            });
        }
    },

    createVehicle: async (vehicle: Omit<Vehicle, 'id'>) => {
        set({ loading: true, error: null });
        try {
            await vehicleService.createVehicle(vehicle);
            const { currentPage, pageSize } = get().pagination;
            await get().fetchVehicles(currentPage, pageSize, get().filter || undefined, get().sortBy || undefined);
        } catch (error) {
            set({
                error: error instanceof Error ? error.message : 'Ошибка при создании',
                loading: false,
            });
        }
    },

    updateVehicle: async (id: number, vehicle: Partial<Vehicle>) => {
        set({ loading: true, error: null });
        try {
            await vehicleService.updateVehicle(id, vehicle);
            const { currentPage, pageSize } = get().pagination;
            await get().fetchVehicles(currentPage, pageSize, get().filter || undefined, get().sortBy || undefined);
        } catch (error) {
            set({
                error: error instanceof Error ? error.message : 'Ошибка при обновлении',
                loading: false,
            });
        }
    },

    deleteVehicle: async (id: number) => {
        set({ loading: true, error: null });
        try {
            await vehicleService.deleteVehicle(id);
            const { currentPage, pageSize } = get().pagination;
            await get().fetchVehicles(currentPage, pageSize, get().filter || undefined, get().sortBy || undefined);
        } catch (error) {
            set({
                error: error instanceof Error ? error.message : 'Ошибка при удалении',
                loading: false,
            });
        }
    },

    setFilter: (filter: VehicleFilterDto | null) => {
        set({ filter });
    },

    setSortBy: (sort: string | null) => {
        set({ sortBy: sort });
    },

    resetFilter: () => {
        set({ filter: null, sortBy: null });
    },
}));
