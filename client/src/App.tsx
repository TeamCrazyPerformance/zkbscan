import Navbar from "./template/sidebar/nav";
import { Routes, Route } from "react-router-dom";

function App() {
  const itemList = [
    { name: "home", key: "/" },
    { name: "Transactions", key: "trans" },
    { name: "L1 -> L2 Transactions", key: "l1l2trans" },
    { name: "L2 -> L1 Transactions", key: "l2l1trans" },
    { name: "Contract internal transactions", key: "cit" },
    { name: "L1 Transactions batches", key: "l1tb" },
    { name: "View L1 state batches", key: "vl1sb" },
    { name: "L2 Transactions", key: "l2t" },
    { name: "L2 Transactions batches", key: "l2tb" },
    { name: "View L2 state batches", key: "vl2sb" },
    { name: "ERC 20 top tokens", key: "e2tt" },
    { name: "ERC 20 transfer", key: "e2t" },
    { name: "ERC 721 top tokens", key: "e7tt" },
    { name: "ERC 721 transfer", key: "e7t" },
    { name: "ERC 1155 top tokens", key: "e1tt" },
    { name: "ERC 1155 transfer", key: "e1t" },
    { name: "Daily transaction chart", key: "dtc" },
  ];

  return (
    <div>
      <Navbar itemList={itemList}></Navbar>
      <Routes>
        {itemList.map((item) => (
          <Route key={item.key} path={`/${item.key}`} element={item.element} />
        ))}
      </Routes>
    </div>
  );
}

export default App;
