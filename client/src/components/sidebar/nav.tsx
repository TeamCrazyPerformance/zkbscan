import { Link } from "react-router-dom";
import {
  CDBSidebar,
  CDBSidebarContent,
  CDBSidebarHeader,
  CDBSidebarMenu,
  CDBSidebarMenuItem,
} from "cdbreact";
import styles from "./Sidebar.module.css";
import { usePage, usePageState } from "../../store/store";

type ComponentProps = {
  itemList: {
    id: number;
    name: string;
    key: string;
  }[];
};

export default function Navbar({ itemList }: ComponentProps) {
  const setId = usePageState((state) => state.setId);
  const setPage = usePage((state) => state.setPage);
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
        <CDBSidebarHeader>
          {" "}
          BNBSCAN
          {/* <img width="51" height="51" src={"../../logo.png"} alt="Logo" /> */}
        </CDBSidebarHeader>
        <CDBSidebarContent>
          <CDBSidebarMenu className={styles.SideBarContainer}>
            {itemList.map((item) => (
              <div key={item.key} className={styles.SideBarItem}>
                <CDBSidebarMenuItem>
                  <Link
                    to={`${item.key}`}
                    onClick={() => {
                      setId(item.id);
                      setPage(1);
                    }}
                  >
                    {item.name}
                  </Link>
                </CDBSidebarMenuItem>
              </div>
            ))}
          </CDBSidebarMenu>
        </CDBSidebarContent>
      </CDBSidebar>
    </div>
  );
}
