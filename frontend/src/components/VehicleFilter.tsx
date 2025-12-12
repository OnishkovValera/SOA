import { useState } from 'react';
import { Button } from '@/components/ui/button';
import type {VehicleFilterDto} from "@/lib/vehicleService.ts";

interface VehicleFilterProps {
    onApply: (filter: VehicleFilterDto, sort?: string) => void;
    onReset: () => void;
}

export function VehicleFilter({ onApply, onReset }: VehicleFilterProps) {
    const [filter, setFilter] = useState<VehicleFilterDto>({});
    const [sort, setSort] = useState<string>('');

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value } = e.target;

        setFilter(prev => ({
            ...prev,
            [name]: value === '' ? undefined : isNaN(Number(value)) ? value : Number(value),
        }));
    };

    const handleApply = () => {
        onApply(filter, sort || undefined);
    };

    const handleReset = () => {
        setFilter({});
        setSort('');
        onReset();
    };

    return (
        <div className="border rounded-lg p-4 mb-4">
            <h3 className="font-semibold mb-3">Фильтры и сортировка</h3>

            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 mb-4">
                <div>
                    <label className="text-sm font-medium">Название</label>
                    <input
                        type="text"
                        name="name"
                        value={filter.name || ''}
                        onChange={handleInputChange}
                        className="w-full px-3 py-2 border rounded-md mt-1"
                        placeholder="Содержит..."
                    />
                </div>

                <div>
                    <label className="text-sm font-medium">X координата</label>
                    <input
                        type="number"
                        name="coordinatesX"
                        value={filter.coordinatesX || ''}
                        onChange={handleInputChange}
                        className="w-full px-3 py-2 border rounded-md mt-1"
                        placeholder="X"
                    />
                </div>

                <div>
                    <label className="text-sm font-medium">Y координата</label>
                    <input
                        type="number"
                        name="coordinatesY"
                        value={filter.coordinatesY || ''}
                        onChange={handleInputChange}
                        className="w-full px-3 py-2 border rounded-md mt-1"
                        placeholder="Y"
                    />
                </div>

                <div>
                    <label className="text-sm font-medium">Мощность</label>
                    <input
                        type="number"
                        name="enginePower"
                        value={filter.enginePower || ''}
                        onChange={handleInputChange}
                        className="w-full px-3 py-2 border rounded-md mt-1"
                        placeholder="Мощность"
                    />
                </div>

                <div>
                    <label className="text-sm font-medium">Количество колес</label>
                    <input
                        type="number"
                        name="numberOfWheels"
                        value={filter.numberOfWheels || ''}
                        onChange={handleInputChange}
                        className="w-full px-3 py-2 border rounded-md mt-1"
                        placeholder="Колеса"
                    />
                </div>

                <div>
                    <label className="text-sm font-medium">Расстояние</label>
                    <input
                        type="number"
                        name="distanceTravelled"
                        value={filter.distanceTravelled || ''}
                        onChange={handleInputChange}
                        className="w-full px-3 py-2 border rounded-md mt-1"
                        placeholder="Расстояние"
                    />
                </div>

                <div>
                    <label className="text-sm font-medium">Тип топлива</label>
                    <select
                        name="fuelType"
                        value={filter.fuelType || ''}
                        onChange={handleInputChange}
                        className="w-full px-3 py-2 border rounded-md mt-1"
                    >
                        <option value="">Все типы</option>
                        <option value="DIESEL">Дизель</option>
                        <option value="KEROSENE">Керосин</option>
                        <option value="PLASMA">Плазма</option>
                    </select>
                </div>

                <div>
                    <label className="text-sm font-medium">Сортировка</label>
                    <select
                        value={sort}
                        onChange={(e) => setSort(e.target.value)}
                        className="w-full px-3 py-2 border rounded-md mt-1"
                    >
                        <option value="">Без сортировки</option>
                        <option value="name,asc">Название (А-Я)</option>
                        <option value="name,desc">Название (Я-А)</option>
                        <option value="enginePower,asc">Мощность (возрастание)</option>
                        <option value="enginePower,desc">Мощность (убывание)</option>
                        <option value="creationDate,desc">Дата создания (новые)</option>
                        <option value="creationDate,asc">Дата создания (старые)</option>
                    </select>
                </div>
            </div>

            <div className="flex gap-2">
                <Button onClick={handleApply} variant="default">
                    Применить
                </Button>
                <Button onClick={handleReset} variant="outline">
                    Сбросить
                </Button>
            </div>
        </div>
    );
}
