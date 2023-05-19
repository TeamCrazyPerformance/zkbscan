import { Link } from "react-router-dom";

type ComponentProps = {
  itemList: {
    name: string;
    key: string;
  }[];
};

export default function Navbar({ itemList }: ComponentProps) {
  return (
    <div className="navbar">
      <h1>Navbar</h1>
      <ul>
        {itemList.map((item) => (
          <li key={item.key}>
            <Link to={`${item.key}`}>{item.name}</Link>
          </li>
        ))}
      </ul>
    </div>
  );
}
