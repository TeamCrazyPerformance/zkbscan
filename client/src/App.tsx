function Home({ value }: { value: string }) {
  function handleClick() {
    console.log("clicked!");
  }

  return (
    <div>
      <div>{/* navbar */}</div>
      <div>
        <div>The BnbScan Explorer</div>
        <div>{/* search bar */}</div>
      </div>
      <div>
        <div>{/* price price */}</div>
        <div>{/* latest transaction and batch*/}</div>
        <div>{/* 14 days history graph */}</div>
      </div>
      <div>
        {/* latest L1 batch */}
        {/* Latest transaction*/}
        {/* Latest L1->L2 transaction */}
        {/* Latest L2->L1 transaction */}
      </div>
      <div>{/* footer */}</div>
    </div>
  );
}
