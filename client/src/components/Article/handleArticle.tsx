import { useEffect, useState } from "react";
import {
  FetchDepositTransaction,
  FetchL2Transactions,
} from "../../api/placeSuggestion";
import { usePage, usePageState } from "../../store/store";
import { l1l2trans, l2trans } from "../../type";
import style from "./table.module.css";

// { id: 0, name: "home", key: "/" },
// { id: 1, name: "Transactions", key: "trans" },
// { id: 2, name: "L1 -> L2 Transactions", key: "l1l2trans" },
// { id: 3, name: "L2 -> L1 Transactions", key: "l2l1trans" },
// { id: 4, name: "L2 Transactions", key: "l2t" },
// { id: 5, name: "ERC 20 top tokens", key: "e2tt" },
// { id: 6, name: "ERC 20 transfer", key: "e2t" },
// { id: 7, name: "NFT top tokens", key: "NFTTT" },
// { id: 8, name: "NFT transfer", key: "NFTT" },
// { id: 9, name: "Daily transaction chart", key: "dtc" },
// { id: 10, name: "TVL chart", key: "dtc" }

function Home() {
  return <></>;
}

function L2Transactions({ page }: { page: number }) {
  const [res, setRes] = useState();
  useEffect(() => {
    FetchL2Transactions(page, 25).then((data) => {
      setRes(data);
    });
  }, []);
  let data: l2trans;
  if (res) {
    data = res;
    console.log(data);
    return (
      <table className={}>
        <tr key={"header"}>
          {Object.keys(data.data[0]).map((key) => (
            <th key={key}>{key}</th>
          ))}
        </tr>
        {data.data.map((item) => (
          <tr key={item.l1Address}>
            {Object.values(item).map((val) => (
              <td key={val}>{val}</td>
            ))}
          </tr>
        ))}
      </table>
    );
  }
  return <></>;
}

function L1ToL2Transactions({ page }: { page: number }) {
  const [res, setRes] = useState();
  useEffect(() => {
    FetchDepositTransaction(page, 25).then((data) => {
      setRes(data);
    });
  }, []);
  let data: l1l2trans;
  if (res) {
    data = res;
    console.log(data);
    return (
      <table>
        <tr key={"header"}>
          {Object.keys(data.data[0]).map((key) => (
            <th key={key}>{key}</th>
          ))}
        </tr>
        {data.data.map((item) => (
          <tr key={item.l1Address}>
            {Object.values(item).map((val) => (
              <td key={val}>{val}</td>
            ))}
          </tr>
        ))}
      </table>
    );
  }
  return <></>;
}

function L2ToL1Transactions() {
  return <></>;
}

function ERC20TopToken() {
  return <></>;
}

function ERC20Transfer() {
  return <></>;
}

function NFTTopToken() {
  return <></>;
}

function NFTTransfer() {
  return <></>;
}

function DailyTransactionChart() {
  return <></>;
}

function TVLChart() {
  return <></>;
}

function ERROR() {
  return <></>;
}

function UsePage() {
  const id = usePageState((state) => state.id);
  const page = usePage((state) => state.page);
  if (id == 0) return <Home />;
  if (id == 3) return <L2Transactions page={page} />;
  if (id == 1) return <L1ToL2Transactions page={page} />;
  if (id == 2) return <L2ToL1Transactions />;
  if (id == 4) return <ERC20TopToken />;
  if (id == 5) return <ERC20Transfer />;
  if (id == 6) return <NFTTopToken />;
  if (id == 7) return <NFTTransfer />;
  if (id == 8) return <DailyTransactionChart />;
  if (id == 9) return <TVLChart />;
  return <ERROR></ERROR>;
}

export default UsePage;
