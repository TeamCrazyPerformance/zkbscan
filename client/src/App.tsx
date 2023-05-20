<<<<<<< HEAD
import Cit from "./template/pages/contrInTrans";
import Dtc from "./template/pages/datilyTransChart";
import E1T from "./template/pages/E11Transfer";
import E1TT from "./template/pages/e11TopToken";
import E21TT from "./template/pages/e21tt";
import E2T from "./template/pages/e2Transfer";
=======
import Cit from "./template/pages/cit";
import Dtc from "./template/pages/dtc";
import E1T from "./template/pages/e1t";
import E1TT from "./template/pages/e1tt";
import E21TT from "./template/pages/e2tt";
import E2T from "./template/pages/e2t";
>>>>>>> bd58378be433bfc298b689144eca238fcb52119e
import E7T from "./template/pages/e7t";
import E7TT from "./template/pages/e7TopToken";
import Home from "./template/pages/home";
import L1L2Trans from "./template/pages/l1L2Trans";
import L1TB from "./template/pages/l1TransBatch";
import L2L1Trans from "./template/pages/l2L1Trans";
import L2T from "./template/pages/l2Trans";
import L2TB from "./template/pages/l2transBatch";
import Trans from "./template/pages/trans";
import VL1SB from "./template/pages/vL1TransBatch";
import VL2SB from "./template/pages/vL2StateBatch";
import Navbar from "./template/sidebar/nav";
import { Routes, Route } from "react-router-dom";

function App() {
  let data={mu:'mu'}
  const itemList = [
    { name: "home", key: "/", element: <Home /> },
    { name: "Transactions", key: "trans", element: <Trans /> },
    { name: "L1 -> L2 Transactions", key: "l1l2trans", element: <L1L2Trans /> },
    { name: "L2 -> L1 Transactions", key: "l2l1trans", element: <L2L1Trans data={data}/> },
    { name: "Contract internal transactions", key: "cit", element: <Cit data={data}/> },
    { name: "L1 Transactions batches", key: "l1tb", element: <L1TB data={data}/> },
    { name: "View L1 state batches", key: "vl1sb", element: <VL1SB /> },
    { name: "L2 Transactions", key: "l2t", element: <L2T /> },
    { name: "L2 Transactions batches", key: "l2tb", element: <L2TB /> },
    { name: "View L2 state batches", key: "vl2sb", element: <VL2SB /> },
    { name: "ERC 20 top tokens", key: "e2tt", element: <E21TT /> },
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
