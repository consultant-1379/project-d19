const externals = {
  apps: [{
    path: "app-1",
    entry: "App1"
  }],
  components: {
    default: [],
    shareable: [{
      path: "retrospective-component",
      entry: "RetrospectiveComponent"
    }, {
      path: "category-component",
      entry: "CategoryComponent"
    }]
  },
  panels: [],
  plugins: []
};
module.exports = externals;