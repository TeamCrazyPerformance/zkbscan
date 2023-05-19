import BasicPage from "../basicPage/basicPage";
import TransBasic from "../transBasic/transBasic";
function Trans() {
  return (
    <div>
      <BasicPage>
        {data.map((data) => {
          <TransBasic data={data} />;
        })}
      </BasicPage>
    </div>
  );
}

export default Trans;
