import React from "react";

function Square({ value }: { value: string }) {
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

export default function Board() {
  return (
    <>
      <div className="board-row">
        <Square value="1"></Square>
        <Square value="2"></Square>
        <Square value="3"></Square>
      </div>
      <div className="board-row">
        <Square value="4"></Square>
        <Square value="5"></Square>
        <Square value="6"></Square>
      </div>
      <div className="board-row">
        <Square value="7"></Square>
        <Square value="8"></Square>
      </div>
    </>
  );
}
