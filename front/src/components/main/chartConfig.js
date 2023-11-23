export const data = {
  labels: [
    2022,
    2021,
    2021,
    2021,
    2021,
    2020,
    2020,
    2020,
    2020,
    2019,
    2019,
    2019,
    2019,
    2018,
    2018,
    2018,
    2018,
    2017,
    2017,
    2017,
    2017,
    2016,
    2016,
    2016,
    2016,
    2015,
    2015,
    2015,
    2015,],
  datasets: [
    {
      label: '분기별 평균 매매가',
      backgroundColor: '#f87979',
      data: [
        28916,
        33456,
        35893,
        35706,
        30498,
        32123,
        39176,
        36515,
        37299,
        25722,
        36722,
        38279,
        38975,
        33446,
        30272,
        35342,
        25637,
        28000,
        33954,
        29977,
        34771,
        26125,
        29771,
        30202,
        26812,
        25607,
        26537,
        26892,
        26405,]
    }
  ]
};

export const options = {
  responsive: false,
  maintainAspectRatio: false,
  scales: {
    xAxes: [{
      display: true,
      scaleLabel: {
        display: true,
        labelString: 'Month'
      }
    }],
    yAxes: [{
      display: true,
      scaleLabel: {
        display: true,
        labelString: 'Value'
      }
    }]
  }
}
