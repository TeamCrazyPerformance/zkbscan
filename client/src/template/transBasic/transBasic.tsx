interface MyComponentProps {
  data: {
    [key: string]: string;
  };
}
const TransBasic = ({ data }: MyComponentProps) => {
  return (
    <div>
      <div className="wrapper">
        <div className="box">{data.txnhash}</div>
        <div className="box">{data.method}</div>
        <div className="box">{data.block}</div>
        <div className="box">{data.age}</div>
        <div className="box">{data.from}</div>
        <div className="box">{data.to}</div>
        <div className="box">{data.confirm}</div>
        <div className="box">{data.value}</div>
        <div className="box">{data.fee}</div>
      </div>
    </div>
  );
};

export default TransBasic;
