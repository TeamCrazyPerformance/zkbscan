interface MyComponentProps {
  data: {
    [key: string]: string;
  };
}
function L1TransBatch({ data }: MyComponentProps) {
  return (
    <>
      <div className="wrapper">
        <div className="box">{data.txnbatch}</div>
        <div className="box">{data.age}</div>
        <div className="box">{data.batchsize}</div>
        <div className="box">{data.L1hash}</div>
        <div className="box">{data.prevTotal}</div>
      </div>
    </>
  );
}

export default L1TransBatch;
