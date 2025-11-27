import {mainColumns, type Vehicle} from "@/components/MainComponent/main.columns.tsx";
import PaginationPage from "@/components/PaginationPage.tsx";
import {DataTable} from "@/components/ui/DataTable.tsx";
import {Button} from "@/components/ui/button.tsx";

function returnVehicle(): Vehicle[] {
    return [
        {
            id: 1,
            make: "Toyota",
            model: "Camry",
            year: 2020,
            price: 24000,
        },
        {
            id: 1,
            make: "Toyota2",
            model: "Camry1",
            year: 2020,
            price: 24000,
        },
        {
            id: 1,
            make: "Toyota3",
            model: "Camry2",
            year: 2020,
            price: 24000,
        },
    ]
}

export interface MainComponentsProps {
    triggerExplosion: () => void
}

function MainComponent(props: MainComponentsProps) {
    return (
        <>
            <div>
                <DataTable columns={mainColumns} data={returnVehicle()}/>
                <Button onClick={props.triggerExplosion} variant="secondary">fyf</Button>
                <PaginationPage></PaginationPage>
            </div>
        </>
    );
}

export default MainComponent;