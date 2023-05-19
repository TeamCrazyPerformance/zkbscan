/** Component for NavigationSideBar Item: param(value: value of list; isActivate: is page active?) **/
function Item({ value, isActivate }: { value: string; isActivate: boolean }) {
  function handleOnClick() {
    console.log(`item(${value}) of NavigationSideBar is Clicked!!`);
  }

  return (
    <>
      <li className={isActivate ? "active" : ""} onClick={handleOnClick}>
        {value}
      </li>
    </>
  );
}

/** Component for map Item Component: param(itemList: Object List of Item)**/
export default function NavigationSideBar({
  itemList,
}: {
  itemList: { name: string; isActivate: boolean }[];
}) {
  return (
    <nav className="sidebar">
      <div className="sidebar-header">
        <h3>side bar header</h3>
      </div>
      <ul className="list-unstyled components">
        {itemList.map((item) => (
          <Item value={item.name} isActivate={item.isActivate} />
        ))}
      </ul>
    </nav>
  );
}
