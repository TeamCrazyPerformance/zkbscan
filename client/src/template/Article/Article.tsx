import LatestBatch from "../latestBatch/latestBatch";
import LatestTrans from "../latestTrans/latestTrans";
import L1ToL2 from "../layertolayer/layer1tolayer2Component";
import L2ToL1 from "../layertolayer/layer2tolayer1Component";
import MainBatch from "../mainBatch/mainBatch";
import MainTrans from "../mainTrans/mainTrans";
import PriceBox from "../priceBox/priceBox";

type ComponentProps = {};

function Article(props: ComponentProps) {
  return (
    <div>
      <div>
        <div>
          <LatestBatch />
        </div>
        <div>
          <LatestTrans />
        </div>
        <div><PriceBox /></div>
      </div>
      <div>
        <div>
          <MainBatch />
        </div>
        <div>
          <MainTrans />
        </div>
        <div><L1ToL2 /><</div>
        <div><L2ToL1 /></div>
      </div>
    </div>
  );
}
export default Article;
