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
      <div onClick={handleBlockNoClick}>{item.blockNo}</div>
      <div onClick={handleL2TxHashClick}>{item.l2TxHash}</div>
      <div>{item.birth.toString()}</div>
      <div onClick={handleL1TxHashClick}>{item.l1TxHash}</div>
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
      <div>
        {itemList.map((item) => (
          <Item item={item} />
        ))}
      </div>
    </>
  );
}
