import { useEffect, useState } from 'react';
import PaginationPage from '@/components/PaginationPage.tsx';
import { DataTable } from '@/components/ui/DataTable.tsx';
import { mainColumns } from '@/lib/dto.tsx';
import VehicleModal from '@/components/VehicleModal.tsx';
import { VehicleFilter } from '@/components/VehicleFilter.tsx';
import { useVehicleStore } from '@/lib/vehicleStore';

export interface MainComponentsProps {
    triggerExplosion: () => void;
    createButtonSpin?: boolean;
}

function MainComponent(props: MainComponentsProps) {
    const { vehicles, pagination, loading, fetchVehicles, filter, sortBy } = useVehicleStore();
    const [currentPage, setCurrentPage] = useState(0);
    const pageSize = 20;

    useEffect(() => {
        fetchVehicles(currentPage, pageSize, filter || undefined, sortBy || undefined);
    }, [currentPage, filter, sortBy, fetchVehicles]);

    const handlePageChange = (page: number) => {
        setCurrentPage(page);
    };

    const handleFilterApply = (newFilter: any, sort?: string) => {
        useVehicleStore.setState({ filter: newFilter, sortBy: sort || null });
        setCurrentPage(0);
    };

    const handleFilterReset = () => {
        useVehicleStore.setState({ filter: null, sortBy: null });
        setCurrentPage(0);
    };

    if (loading) {
        return <div className="w-full flex justify-center p-4">Загрузка...</div>;
    }

    return (
        <div className="p-4">
            <div className="mb-4 bg-gray-900">
                <VehicleFilter onApply={handleFilterApply} onReset={handleFilterReset} />
            </div>

            <div className="w-full flex justify-center mb-4">
                <DataTable columns={mainColumns} data={vehicles} />
            </div>

            <div className="w-full flex justify-center mb-4">
                <PaginationPage
                    currentPage={pagination.currentPage}
                    totalPages={pagination.totalPages}
                    onPageChange={handlePageChange}
                />
            </div>

            <div className="w-full flex justify-center">
                <VehicleModal
                    explosionTrigger={props.triggerExplosion}
                    createButtonSpin={props.createButtonSpin}
                    onSave={() => fetchVehicles(currentPage, pageSize, filter || undefined, sortBy || undefined)}
                />
            </div>
        </div>
    );
}

export default MainComponent;
