function VL2StateBatch() {
  return <></>;
}
interface MyComponentProps {
  data: {
    [key: string]: string;
  };
}
const VL2StateBatchBasic = ({ data }: MyComponentProps) => {
  return (
    <div>
      <div className="wrapper">
        <div className="box">{data.statebatch}</div>
        <div className="box">{data.age}</div>
        <div className="box">{data.batchsize}</div>
        <div className="box">{data.L1hash}</div>
        <div className="box">{data.prevTotal}</div>
      </div>
    </div>
  );
};

export default VL2StateBatch;
