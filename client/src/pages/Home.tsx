import { useNavStore } from "../store/store";
import Sidebar from "../components/sidebar/nav";
import Header from "../components/Header/header";
import Article from "../components/Article/Article";
import style from "./Home.module.css";

function Home() {
  const navList = useNavStore((state) => state.navList);
  return (
    <div className="wrapper">
      <Sidebar itemList={navList} />
      <div className={style.ContentContainer}>
        <Header />
        <Article />
      </div>
    </div>
  );
}

export default Home;
