interface MyComponentProps {
  data: {
    [key: string]: string;
  };
}

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
