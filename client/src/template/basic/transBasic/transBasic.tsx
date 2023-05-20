interface MyComponentProps {
  data: {
    [key: string]: string;
  };
}
const TransBasic = ({ data }: MyComponentProps) => {
  return <div></div>;
};

export default TransBasic;
