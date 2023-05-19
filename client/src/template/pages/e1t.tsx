function E1T() {
  async function logJSONData() {
    const response = await fetch(
      "http://172.16.41.132:8080/block/l1/0x78E152a9676E9C044fB30980885896dDa48FB81E"
    );
    const jsonData = await response.json();
    console.log(jsonData);
  }
  return (
    <>
      <button onClick={logJSONData}>asdfasdf</button>
    </>
  );
}

export default E1T;
