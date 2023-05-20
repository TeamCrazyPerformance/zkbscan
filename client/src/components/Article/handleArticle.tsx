import { useEffect, useState } from "react";
import {
  FetchDepositTransaction,
  FetchL2Transactions,
} from "../../api/placeSuggestion";
import { usePage, usePageState } from "../../store/store";
import { l1l2trans, l2trans } from "../../type";
import style from "./table.module.css";
import LineChart from "../chart/chart";

interface Data {
  bnbPrice: number;
  latestL1BlockNumber: number;
  latestl2BlockNumber: number;
  tvl: number;
  totalTransactionCount: number;
}
interface Stat {
  date: string;
  transactionCount: number;
  price: number;
}
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
  let [data, setData] = useState<Data>({
    bnbPrice: 0,
    latestL1BlockNumber: 0,
    latestl2BlockNumber: 0,
    tvl: 0,
    totalTransactionCount: 0,
  });
  let [stat, setStat] = useState<Stat>({
    date: "1",
    transactionCount: 0,
    price: 0,
  });
  useEffect(() => {
    fetch("http://172.16.41.132:8080/statistics")
      .then((response) => response.json())
      .then((data) => {
        // Process the received data
        // Example: Set the received data to the component's state
        setData(data);
      })
      .catch((error) => {
        // Handle errors if necessary
        console.error("Error fetching data:", error);
      });
    fetch("http://172.16.41.132:8080/statistics/chart")
      .then((response) => response.json())
      .then((data) => {
        // Process the received data
        // Example: Set the received data to the component's state
        setData(data);
      })
      .catch((error) => {
        // Handle errors if necessary
        console.error("Error fetching data:", error);
      });
  }, []);

  return (
    <div className={style.flex}>
      {/* <div>
        <img src="../../bnb.svg" alt="bnb" />
      </div> */}

      <div className={style.wrap}>
        <div>
          <div>BNB PRICE</div>
          <div>${data.bnbPrice}</div>
        </div>
        <div>
          <div>TLV</div>
          <div>${data.tvl}</div>
        </div>
      </div>
      <div className={style.wrap}>
        <div>
          <div>latestL1BlockNumber</div>
          <div>{data.latestL1BlockNumber}</div>
        </div>

        <div className={style.wrap}>
          <div>
            <div>latestl2BlockNumber</div>
            <div>{data.latestl2BlockNumber}</div>
          </div>
        </div>
      </div>

      <div className={style.wrap}>
        <div>
          <div>totalTransactionCount</div>
          <div>{data.totalTransactionCount}</div>
        </div>
      </div>
      <LineChart />
    </div>
  );
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
      <table className={style.good}>
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
  // if (id == 9) return <TVLChart />;
  return <></>;
}

export default UsePage;
