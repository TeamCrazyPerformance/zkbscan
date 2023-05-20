function VL1TransBatch() {
  return <></>;
}

interface MyComponentProps {
  data: {
    [key: string]: string;
  };
}
const VL1TransBatchBasic = ({ data }: MyComponentProps) => {
  return (
    <div>
      <div className="wrapper">
        <div className="box">{data.txnbatch}</div>
        <div className="box">{data.age}</div>
        <div className="box">{data.batchsize}</div>
        <div className="box">{data.L1hash}</div>
        <div className="box">{data.prevTotal}</div>
      </div>
    </div>
  );
};

export default VL1TransBatch;
