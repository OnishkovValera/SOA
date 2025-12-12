import type {ColumnDef} from "@tanstack/react-table";
import VehicleModal from "@/components/VehicleModal.tsx";

export interface Vehicle {
    id: number
    name: string
    coordinate: Coordinate
    creationDate: Date
    enginePower: number
    numberOfWheels: number
    distanceTravelled: number
    fuelType: "KEROSENE" | "DIESEL" | "PLASMA"
}

export interface Coordinate {
    x: number
    y: number
}

export const mainColumns: ColumnDef<Vehicle>[] = [
    {
        accessorKey: "id",
        header: "id"
    },
    {
        accessorKey: "name",
        header: "name"
    },
    {
        accessorKey: "coordinate.x",
        header: "x"
    },
    {
        accessorKey: "coordinate.y",
        header: "y"
    },
    {
        accessorKey: "creationDate",
        header: "date"
    },
    {
        accessorKey: "enginePower",
        header: "enginePower"
    },
    {
        accessorKey: "numberOfWheels",
        header: "numberOfWheels"
    },
    {
        accessorKey: "distanceTravelled",
        header: "distanceTravelled"
    },
    {
        accessorKey: "fuelType",
        header: "fuelType"
    },
    {
        id: "actions",
        header: "Действия",
        cell: ({row}) => {
            const vehicle = row.original;

            return (
                <VehicleModal
                    vehicle={vehicle}
                    explosionTrigger={() => console.log("Trigger!")}
                    createButtonSpin={false}
                />
            )
                ;
        },
    },
]