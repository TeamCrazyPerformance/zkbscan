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
      <li onClick={handleFromClick}>{item.from}</li>
      <li onClick={handleL2TxHashClick}>{item.l2TxHash}</li>
      <li>{item.birth.toString()}</li>
      <li>{item.status}</li>
      <li onClick={handleL1TxnHash}>{item.l1TxnHash}</li>
    </>
  );
}

export default function L1ToL2({
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
      <ul>
        {itemList.map((item) => (
          <Item item={item} />
        ))}
      </ul>
    </>
  );
}
