import React from "react";
import {
  VictoryChart,
  VictoryLine,
  VictoryAxis,
  VictoryTooltip,
  VictoryVoronoiContainer,
} from "victory";

interface IDataPoint {
  date: string;
  transactionCount: number;
  price: number;
}

interface ILineChartProps {
  data: IDataPoint[];
}

const LineChart: React.FC<ILineChartProps> = ({ data }) => {
  return (
    <VictoryChart containerComponent={<VictoryVoronoiContainer />}>
      <VictoryAxis dependentAxis tickFormat={(tick) => tick.toFixed(1)} />
      <VictoryAxis tickFormat={(date) => new Date(date).toLocaleDateString()} />
      <VictoryLine
        data={data}
        x="date"
        y="transactionCount"
        labelComponent={<VictoryTooltip />}
        labels={({ datum }) =>
          `Date: ${datum.date}\nTransaction Count: ${datum.transactionCount}\nPrice: ${datum.price}`
        }
      />
    </VictoryChart>
  );
};

export default LineChart;
