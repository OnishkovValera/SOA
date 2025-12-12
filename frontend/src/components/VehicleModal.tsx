import {useState, useEffect} from "react";
import {
    Dialog,
    DialogContent,
    DialogDescription,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
} from "@/components/ui/dialog";
import {Button} from "@/components/ui/button";
import {type Vehicle} from "@/lib/dto.tsx";
import { cn } from "@/lib/utils.ts";
import { useVehicleStore } from "@/lib/vehicleStore.ts";

interface VehicleModalProps {
    vehicle?: Vehicle;
    onSave?: (vehicle: Vehicle) => void;
    explosionTrigger: () => void;
    createButtonSpin?: boolean;
}

interface Coordinate {
    x: number;
    y: number | null;
}

interface FormData {
    name: string;
    coordinate: Coordinate;
    enginePower: number;
    numberOfWheels: number;
    distanceTravelled: number;
    fuelType: "DIESEL" | "KEROSENE" | "PLASMA";
}

type Errors = Partial<Record<"name" | "x" | "y" | "enginePower" | "numberOfWheels" | "distanceTravelled", string>>;

function VehicleModal({vehicle, onSave, explosionTrigger, createButtonSpin}: VehicleModalProps) {
    const { createVehicle, updateVehicle } = useVehicleStore();
    const [open, setOpen] = useState(false);
    const [formData, setFormData] = useState<FormData>({
        name: "",
        coordinate: {x: 0, y: null},
        enginePower: 0,
        numberOfWheels: 0,
        distanceTravelled: 0,
        fuelType: "DIESEL",
    });

    const [errors, setErrors] = useState<Errors>({});

    useEffect(() => {
        if (vehicle) {
            setFormData({
                name: vehicle.name,
                coordinate: {
                    x: vehicle.coordinate.x,
                    y: vehicle.coordinate.y || null,
                },
                enginePower: vehicle.enginePower,
                numberOfWheels: vehicle.numberOfWheels,
                distanceTravelled: vehicle.distanceTravelled,
                fuelType: vehicle.fuelType,
            });
        } else {
            setFormData({
                name: "",
                coordinate: {x: 0, y: null},
                enginePower: 0,
                numberOfWheels: 0,
                distanceTravelled: 0,
                fuelType: "DIESEL",
            });
        }
        setErrors({});
    }, [vehicle, open]);

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const {name, value} = e.target;

        if (name === "x" || name === "y") {
            const parsed = value === "" ? null : parseFloat(value);
            setFormData(prev => ({
                ...prev,
                coordinate: {
                    ...prev.coordinate,
                    [name]: parsed,
                },
            }));
        } else if (name === "enginePower" || name === "numberOfWheels" || name === "distanceTravelled") {
            const parsed = value === "" ? 0 : parseFloat(value);
            setFormData(prev => ({
                ...prev,
                [name]: parsed,
            }));
        } else {
            setFormData(prev => ({
                ...prev,
                [name]: value,
            }));
        }

        setErrors(prev => ({...prev, [name]: undefined}));
    };

    const validate = (): boolean => {
        const newErrors: Errors = {};

        if (!formData.name || !/\S/.test(formData.name)) {
            newErrors.name = "Название не должно быть пустым или состоять только из пробелов.";
        }

        if (formData.coordinate.x === null || isNaN(Number(formData.coordinate.x))) {
            newErrors.x = "X координата обязательна.";
        } else if (Number(formData.coordinate.x) > 61) {
            newErrors.x = "X координата должна быть ≤ 61.";
        }

        if (formData.coordinate.y !== null && isNaN(Number(formData.coordinate.y))) {
            newErrors.y = "Y координата должна быть числом или оставьте пустой.";
        }

        if (formData.enginePower === 0 || isNaN(Number(formData.enginePower))) {
            newErrors.enginePower = "Мощность обязательна.";
        } else if (Number(formData.enginePower) <= 0) {
            newErrors.enginePower = "Мощность должна быть положительным числом.";
        }

        if (formData.numberOfWheels === 0 || isNaN(Number(formData.numberOfWheels))) {
            newErrors.numberOfWheels = "Количество колес обязательно.";
        } else if (!Number.isInteger(Number(formData.numberOfWheels)) || Number(formData.numberOfWheels) <= 0) {
            newErrors.numberOfWheels = "Количество колес должно быть положительным целым числом.";
        }

        if (formData.distanceTravelled === 0 || isNaN(Number(formData.distanceTravelled))) {
            newErrors.distanceTravelled = "Расстояние обязательно.";
        } else if (!Number.isInteger(Number(formData.distanceTravelled)) || Number(formData.distanceTravelled) <= 0) {
            newErrors.distanceTravelled = "Расстояние должно быть положительным целым числом.";
        }

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const handleSave = async () => {
        if (!validate()) return;

        const prepared = {
            name: formData.name,
            coordinate: {
                x: Number(formData.coordinate.x),
                y: formData.coordinate.y === null ? null : Number(formData.coordinate.y),
            },
            enginePower: Number(formData.enginePower),
            numberOfWheels: Number(formData.numberOfWheels),
            distanceTravelled: Number(formData.distanceTravelled),
            fuelType: formData.fuelType,
        };

        try {
            if (vehicle && vehicle.id) {
                await updateVehicle(vehicle.id, prepared as Partial<Vehicle>);
            } else {
                await createVehicle(prepared as Omit<Vehicle, "id">);
            }

            if (onSave) {
                onSave(prepared as Vehicle);
            }
            explosionTrigger();
            setOpen(false);
        } catch (error) {
            console.error('Ошибка при сохранении:', error);
        }
    };

    const hasErrors = Object.values(errors).some(error => error !== undefined);

    return (
        <Dialog open={open} onOpenChange={setOpen}>
            <DialogTrigger asChild>
                <Button variant="default" className={cn(createButtonSpin && "animate-spin")}>
                    {vehicle ? "Редактировать" : "Создать Vehicle"}
                </Button>
            </DialogTrigger>
            <DialogContent className="sm:max-w-[425px]">
                <DialogHeader>
                    <DialogTitle>{vehicle ? "Редактировать Vehicle" : "Создать новый Vehicle"}</DialogTitle>
                    <DialogDescription>
                        Заполните данные для {vehicle ? "обновления" : "создания"} транспортного средства
                    </DialogDescription>
                </DialogHeader>
                <div className="grid gap-4 py-4">
                    <div className="grid grid-cols-4 items-center gap-4">
                        <label htmlFor="name" className="text-right text-sm font-medium">
                            Название
                        </label>
                        <div className="col-span-3">
                            <input
                                id="name"
                                name="name"
                                value={formData.name}
                                onChange={handleInputChange}
                                className="w-full px-3 py-2 border rounded-md"
                                placeholder="Введите название"
                            />
                            {errors.name && <div className="text-red-600 text-sm mt-1">{errors.name}</div>}
                        </div>
                    </div>

                    <div className="grid grid-cols-4 items-center gap-4">
                        <label htmlFor="x" className="text-right text-sm font-medium">
                            X координата
                        </label>
                        <div className="col-span-3">
                            <input
                                id="x"
                                name="x"
                                type="number"
                                value={formData.coordinate.x}
                                onChange={handleInputChange}
                                className="w-full px-3 py-2 border rounded-md"
                                step="0.001"
                            />
                            {errors.x && <div className="text-red-600 text-sm mt-1">{errors.x}</div>}
                        </div>
                    </div>

                    <div className="grid grid-cols-4 items-center gap-4">
                        <label htmlFor="y" className="text-right text-sm font-medium">
                            Y координата
                        </label>
                        <div className="col-span-3">
                            <input
                                id="y"
                                name="y"
                                type="number"
                                value={formData.coordinate.y ?? ""}
                                onChange={handleInputChange}
                                className="w-full px-3 py-2 border rounded-md"
                                step="0.001"
                                placeholder="Можно оставить пустым"
                            />
                            {errors.y && <div className="text-red-600 text-sm mt-1">{errors.y}</div>}
                        </div>
                    </div>

                    <div className="grid grid-cols-4 items-center gap-4">
                        <label htmlFor="enginePower" className="text-right text-sm font-medium">
                            Мощность
                        </label>
                        <div className="col-span-3">
                            <input
                                id="enginePower"
                                name="enginePower"
                                type="number"
                                value={formData.enginePower}
                                onChange={handleInputChange}
                                className="w-full px-3 py-2 border rounded-md"
                            />
                            {errors.enginePower && <div className="text-red-600 text-sm mt-1">{errors.enginePower}</div>}
                        </div>
                    </div>

                    <div className="grid grid-cols-4 items-center gap-4">
                        <label htmlFor="numberOfWheels" className="text-right text-sm font-medium">
                            Колеса
                        </label>
                        <div className="col-span-3">
                            <input
                                id="numberOfWheels"
                                name="numberOfWheels"
                                type="number"
                                value={formData.numberOfWheels}
                                onChange={handleInputChange}
                                className="w-full px-3 py-2 border rounded-md"
                            />
                            {errors.numberOfWheels && <div className="text-red-600 text-sm mt-1">{errors.numberOfWheels}</div>}
                        </div>
                    </div>

                    <div className="grid grid-cols-4 items-center gap-4">
                        <label htmlFor="distanceTravelled" className="text-right text-sm font-medium">
                            Расстояние
                        </label>
                        <div className="col-span-3">
                            <input
                                id="distanceTravelled"
                                name="distanceTravelled"
                                type="number"
                                value={formData.distanceTravelled}
                                onChange={handleInputChange}
                                className="w-full px-3 py-2 border rounded-md"
                                step="0.01"
                            />
                            {errors.distanceTravelled && <div className="text-red-600 text-sm mt-1">{errors.distanceTravelled}</div>}
                        </div>
                    </div>

                    <div className="grid grid-cols-4 items-center gap-4">
                        <label htmlFor="fuelType" className="text-right text-sm font-medium">
                            Топливо
                        </label>
                        <select
                            id="fuelType"
                            name="fuelType"
                            value={formData.fuelType}
                            onChange={handleInputChange}
                            className="col-span-3 px-3 py-2 border rounded-md"
                        >
                            <option value="DIESEL">Дизель</option>
                            <option value="KEROSENE">Керосин</option>
                            <option value="PLASMA">Плазма</option>
                        </select>
                    </div>
                </div>

                <div className="flex justify-end gap-3">
                    <Button variant="outline" onClick={() => {
                        setOpen(false);
                        explosionTrigger();
                    }}>
                        Отмена
                    </Button>
                    <Button onClick={handleSave} disabled={hasErrors}>
                        Сохранить
                    </Button>
                </div>
            </DialogContent>
        </Dialog>
    );
}

export default VehicleModal;
