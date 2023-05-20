import { Link } from "react-router-dom";
import {
  CDBSidebar,
  CDBSidebarContent,
  CDBSidebarHeader,
  CDBSidebarMenu,
  CDBSidebarMenuItem,
} from "cdbreact";
import styles from "./Sidebar.module.css";

type ComponentProps = {
  itemList: {
    name: string;
    key: string;
  }[];
};

export default function Navbar({ itemList }: ComponentProps) {
  return (
    <div className={styles.SideBarContainer}>
      <CDBSidebar
        className={""}
        textColor={"black"}
        backgroundColor={"#F0B20B"}
        breakpoint={0}
        toggled={false}
        minWidth={""}
        maxWidth={"300px"}
      >
        <CDBSidebarHeader>Navbar</CDBSidebarHeader>
        <CDBSidebarContent>
          <CDBSidebarMenu>
            {itemList.map((item) => (
              <div key={item.key} className={styles.SideBarItem}>
                <CDBSidebarMenuItem>
                  <Link to={`${item.key}`}>{item.name}</Link>
                </CDBSidebarMenuItem>
              </div>
            ))}
          </CDBSidebarMenu>
        </CDBSidebarContent>
      </CDBSidebar>
    </div>
  );
}
