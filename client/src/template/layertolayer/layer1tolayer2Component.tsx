function Item({
  item,
}: {
  item: { blockNo: number; l2TxHash: string; birth: Date; l1TxHash: string };
}) {
  function handleL1TxHashClick() {
    console.log("l1TxHash clicked!!");
  }

  function handleL2TxHashClick() {
    console.log("l2TxHash clicked!!");
  }

  function handleBlockNoClick() {
    console.log("blockNo clicked!!");
  }
  return (
    <>
      <li onClick={handleBlockNoClick}>{item.blockNo}</li>
      <li onClick={handleL2TxHashClick}>{item.l2TxHash}</li>
      <li>{item.birth.toString()}</li>
      <li onClick={handleL1TxHashClick}>{item.l1TxHash}</li>
    </>
  );
}

export default function L1ToL2({
  itemList,
}: {
  itemList: {
    blockNo: number;
    l2TxHash: string;
    birth: Date;
    l1TxHash: string;
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
