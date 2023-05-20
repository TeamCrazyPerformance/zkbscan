interface MyComponentProps {
  data: {
    [key: string]: string;
  };
}

function L1L2Trans() {
  return <></>;
}

const L1L2Basic = ({ data }: MyComponentProps) => {
  return (
    <div>
      <div className="wrapper">
        <div className="box">{data.blocknum}</div>
        <div className="box">{data.queindex}</div>
        <div className="box">{data.L2hash}</div>
        <div className="box">{data.age}</div>
        <div className="box">{data.L1hash}</div>
        <div className="box">{data.L1origin}</div>
        <div className="box">{data.gaslimit}</div>
      </div>
    </div>
  );
};

export default L1L2Trans;
