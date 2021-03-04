import app from './app.js'
import name from './name.js'
import tps from './tps'

const config = Object.assign(
    app,
    name,
    tps,
)

export default config;
