import { Status } from "./domain/statusEnum";

function Item({
  item,
}: {
  item: {
    from: string;
    l2TxHash: string;
    birth: Date;
    status: Status;
    l1TxnHash: string;
  };
}) {
  function handleFromClick() {
    console.log("from clicked!!");
  }
  function handleL2TxHashClick() {
    console.log("l2TxHash clicked!!");
  }
  function handleL1TxnHash() {
    console.log("l1TxnHash clicked!!");
  }
  return (
    <>
      <div onClick={handleFromClick}>{item.from}</div>
      <div onClick={handleL2TxHashClick}>{item.l2TxHash}</div>
      <div>{item.birth.toString()}</div>
      <div>{item.status}</div>
      <div onClick={handleL1TxnHash}>{item.l1TxnHash}</div>
    </>
  );
}

export default function L2ToL1({
  itemList,
}: {
  itemList: {
    from: string;
    l2TxHash: string;
    birth: Date;
    status: Status;
    l1TxnHash: string;
  }[];
}) {
  return (
    <>
      <div>
        {itemList.map((item) => (
          <Item item={item} />
        ))}
      </div>
    </>
  );
}
