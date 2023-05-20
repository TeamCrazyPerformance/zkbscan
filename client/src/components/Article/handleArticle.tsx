import { usePageState } from "../../store/store";

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

function Transactions() {
  return <></>;
}

function L1ToL2Transactions() {
  return <></>;
}

function L2ToL1Transactions() {
  return <></>;
}

function L2Transactions() {
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
  if (id == 0) return <Home />;
  if (id == 1) return <Transactions />;
  if (id == 2) return <L1ToL2Transactions />;
  if (id == 3) return <L2ToL1Transactions />;
  if (id == 4) return <L2Transactions />;
  if (id == 5) return <ERC20TopToken />;
  if (id == 6) return <ERC20Transfer />;
  if (id == 7) return <NFTTopToken />;
  if (id == 8) return <NFTTransfer />;
  if (id == 9) return <DailyTransactionChart />;
  if (id == 10) return <TVLChart />;
  return <ERROR></ERROR>;
}

export default UsePage();
