const {app, BrowserWindow} = require('electron')

let win;

function createWindow() {
  win = new BrowserWindow({
    width: screen.width,
    height: screen.height,
  })
}

win.loadURL('FastFoodManagementFrontEnd/dist/fast-food-management-front-end/index.html')

win.on('closed', function () {
  win = null
})

app.on('ready', createWindow)

if (process.platform !== 'darwin') {
  app.quit()
}
app.on('activate', function () {
  if (win === null) {
    createWindow()
  }
})
