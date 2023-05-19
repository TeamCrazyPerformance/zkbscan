interface MyComponentProps {
  data: {
    [key: string]: string;
  };
}
const L2L1Basic = ({ data }: MyComponentProps) => {
  return (
    <div>
      <div className="wrapper">
        <div className="box">{data.msgnonce}</div>
        <div className="box">{data.from}</div>
        <div className="box">{data.L2hash}</div>
        <div className="box">{data.age}</div>
        <div className="box">{data.status}</div>
        <div className="box">{data.L1hash}</div>
        <div className="box">{data.timeleft}</div>
      </div>
    </div>
  );
};

export default L2L1Basic;
