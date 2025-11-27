import type {ColumnDef} from "@tanstack/react-table";

export interface Vehicle {
    id: number;
    make: string;
    model: string;
    year: number;
    price: number;
}

export const mainColumns: ColumnDef<Vehicle>[] = [
    {
        accessorKey: "id",
        header: "id"
    },
    {
        accessorKey: "make",
        header: "Make"
    },
    {
        accessorKey: "model",
        header: "model"
    },
    {
        accessorKey: "year",
        header: "year"
    },
    {
        accessorKey: "make",
        header: "price"
    }
]