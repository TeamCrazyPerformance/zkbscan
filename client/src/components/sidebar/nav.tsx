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
      <div>Navbar</div>
      <div>
        {itemList.map((item) => (
          <div key={item.key}>
            <Link to={`${item.key}`}>{item.name}</Link>
          </div>
        ))}
      </div>
    </div>
  );
}
