interface MyComponentProps {
  data: {
    [key: string]: string;
  };
}
const Block = ({ data }: MyComponentProps) => {
  return (
    <div>
      <div className="wrapper">
        <div className="box">{data.block}</div>
        <div className="box">{data.age}</div>
        <div className="box">{data.txn}</div>
        <div className="box">{data.gasUsed}</div>
        <div className="box">{data.gasLimit}</div>
      </div>
    </div>
  );
};

export default Block;
