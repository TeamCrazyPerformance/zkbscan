import { useNavStore } from "../store/store";
import Sidebar from "../components/sidebar/nav";

function Home() {
  const navList = useNavStore((state) => state.navList);
  return (
    <>
      <Header />
      <Sidebar itemList={navList} />
      <Article />
      <Footer />
    </>
  );
}
