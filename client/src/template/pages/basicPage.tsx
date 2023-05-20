import { useState, ReactNode } from "react";

interface TabularLayoutProps {
  title: string;
  description: string;
  children: ReactNode;
}

const basicPage: React.FC<TabularLayoutProps> = ({
  title,
  description,
  children,
}) => {
  const [currentPage, setCurrentPage] = useState(1);

  const nextPage = () => {
    setCurrentPage(currentPage + 1);
  };
  const prevPage = () => {
    setCurrentPage(currentPage - 1);
  };
  const firstPage = () => {
    setCurrentPage(1);
  };
  const lastPage = () => {
    setCurrentPage(100);
  };

  return (
    <div className="table-component">
      <h2>{title}</h2>
      <p>{description}</p>
      <div className="page-container">
        <button onClick={firstPage}>First</button>
        <button onClick={prevPage}>-</button>
        <span>Current Page: {currentPage}</span>
        <button onClick={nextPage}>+</button>
        <button onClick={lastPage}>Last</button>
      </div>
      <div>{children}</div>
    </div>
  );
};

export default basicPage;
