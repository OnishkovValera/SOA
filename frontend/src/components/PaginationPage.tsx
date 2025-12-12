import {
    Pagination,
    PaginationContent,
    PaginationEllipsis,
    PaginationItem,
    PaginationLink,
    PaginationNext,
    PaginationPrevious,
} from "@/components/ui/pagination.tsx";

interface PaginationPageProps {
    currentPage: number;
    totalPages: number;
    onPageChange: (page: number) => void;
}

export default function PaginationPage({ currentPage, totalPages, onPageChange }: PaginationPageProps) {
    const handlePrevious = () => {
        if (currentPage > 0) {
            onPageChange(currentPage - 1);
        }
    };

    const handleNext = () => {
        if (currentPage < totalPages - 1) {
            onPageChange(currentPage + 1);
        }
    };

    const handlePageClick = (page: number) => {
        onPageChange(page);
    };

    const renderPageNumbers = () => {
        const pages = [];
        const maxVisible = 5;

        if (totalPages <= maxVisible) {
            for (let i = 0; i < totalPages; i++) {
                pages.push(
                    <PaginationItem key={i}>
                        <PaginationLink
                            onClick={() => handlePageClick(i)}
                            isActive={i === currentPage}
                        >
                            {i + 1}
                        </PaginationLink>
                    </PaginationItem>
                );
            }
        } else {
            for (let i = 0; i < Math.min(3, totalPages); i++) {
                pages.push(
                    <PaginationItem key={i}>
                        <PaginationLink
                            onClick={() => handlePageClick(i)}
                            isActive={i === currentPage}
                        >
                            {i + 1}
                        </PaginationLink>
                    </PaginationItem>
                );
            }
            if (currentPage > 3) {
                pages.push(<PaginationEllipsis key="ellipsis-start" />);
            }
            if (currentPage > 2 && currentPage < totalPages - 3) {
                pages.push(
                    <PaginationItem key={currentPage}>
                        <PaginationLink
                            onClick={() => handlePageClick(currentPage)}
                            isActive
                        >
                            {currentPage + 1}
                        </PaginationLink>
                    </PaginationItem>
                );
            }
            if (currentPage < totalPages - 3) {
                pages.push(<PaginationEllipsis key="ellipsis-end" />);
            }
            for (let i = Math.max(totalPages - 3, 0); i < totalPages; i++) {
                pages.push(
                    <PaginationItem key={i}>
                        <PaginationLink
                            onClick={() => handlePageClick(i)}
                            isActive={i === currentPage}
                        >
                            {i + 1}
                        </PaginationLink>
                    </PaginationItem>
                );
            }
        }

        return pages;
    };

    return (
        <Pagination>
            <PaginationContent>
                <PaginationItem>
                    <PaginationPrevious
                        onClick={handlePrevious}
                        className={currentPage === 0 ? "pointer-events-none opacity-50" : "cursor-pointer"}
                    />
                </PaginationItem>
                {renderPageNumbers()}
                <PaginationItem>
                    <PaginationNext
                        onClick={handleNext}
                        className={currentPage === totalPages - 1 ? "pointer-events-none opacity-50" : "cursor-pointer"}
                    />
                </PaginationItem>
            </PaginationContent>
        </Pagination>
    );
}
