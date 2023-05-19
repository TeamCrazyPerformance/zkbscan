interface MyComponentProps {
  data: {
    [key: string]: string;
  };
}
const CitBasic = ({ data }: MyComponentProps) => {
  return (
    <div>
      <div className="wrapper">
        <div className="box">{data.block}</div>
        <div className="box">{data.age}</div>
        <div className="box">{data.parenthash}</div>
        <div className="box">{data.type}</div>
        <div className="box">{data.from}</div>
        <div className="box">{data.to}</div>
        <div className="box">{data.value}</div>
      </div>
    </div>
  );
};

export default CitBasic;
