import Article from "./template/Article/Article";
import NavigationSideBar from "./template/sidebar/nav";

export default function Home() {
  return (
    <div>
      <div>
        <NavigationSideBar />
      </div>
      <div>
        <div>The BnbScan Explorer</div>
        <div>{/* search bar */}</div>
      </div>
      <Article />
      <div>{/* footer */}</div>
    </div>
  );
}
