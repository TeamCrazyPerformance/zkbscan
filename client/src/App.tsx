import Cit from "./template/pages/cit";
import Dtc from "./template/pages/dtc";
import E1T from "./template/pages/e1t";
import E1TT from "./template/pages/e1tt";
import E21TT from "./template/pages/e21tt";
import E2T from "./template/pages/e2t";
import E7T from "./template/pages/e7t";
import E7TT from "./template/pages/e7tt";
import Home from "./template/pages/home";
import L1L2Trans from "./template/pages/l1l2trans";
import L1TB from "./template/pages/l1tb";
import L2L1Trans from "./template/pages/l2l1trans";
import L2T from "./template/pages/l2t";
import L2TB from "./template/pages/l2tb";
import Trans from "./template/pages/trans";
import VL1SB from "./template/pages/vl1sb";
import VL2SB from "./template/pages/vl2sb";
import Navbar from "./template/sidebar/nav";
import { Routes, Route } from "react-router-dom";

function App() {
  const itemList = [
    { name: "home", key: "/", element: <Home /> },
    { name: "Transactions", key: "trans", element: <Trans /> },
    { name: "L1 -> L2 Transactions", key: "l1l2trans", element: <L1L2Trans /> },
    { name: "L2 -> L1 Transactions", key: "l2l1trans", element: <L2L1Trans /> },
    { name: "Contract internal transactions", key: "cit", element: <Cit /> },
    { name: "L1 Transactions batches", key: "l1tb", element: <L1TB /> },
    { name: "View L1 state batches", key: "vl1sb", element: <VL1SB /> },
    { name: "L2 Transactions", key: "l2t", element: <L2T /> },
    { name: "L2 Transactions batches", key: "l2tb", element: <L2TB /> },
    { name: "View L2 state batches", key: "vl2sb", element: <VL2SB /> },
    { name: "ERC 20 top tokens", key: "e21tt", element: <E21TT /> },
    { name: "ERC 20 transfer", key: "e2t", element: <E2T /> },
    { name: "ERC 721 top tokens", key: "e7tt", element: <E7TT /> },
    { name: "ERC 721 transfer", key: "e7t", element: <E7T /> },
    { name: "ERC 1155 top tokens", key: "e1tt", element: <E1TT /> },
    { name: "ERC 1155 transfer", key: "e1t", element: <E1T /> },
    { name: "Daily transaction chart", key: "dtc", element: <Dtc /> },
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
