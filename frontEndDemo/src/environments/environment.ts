// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  getApi: 'http://localhost:8080/api/useri/all',
  loginApi: 'http://localhost:8080/auth/login',
  insertApi: 'http://localhost:8080/api/useri',
  deleteApi: 'http://localhost:8080/api/useri',
  createMachineApi: 'http://localhost:8080/api/machines',
  showMachinesApi: 'http://localhost:8080/api/machines/machineUser',
  destroyMachineApi: 'http://localhost:8080/api/machines',
  searchParametersApi: 'http://localhost:8080/api/machines',
  startMachineApi: 'http://localhost:8080/api/ssr/startMachine',
  stopMachineApi: 'http://localhost:8080/api/ssr/stopMachine',
  restartMachineApi: 'http://localhost:8080/api/ssr/restartMachine',
  reservationStartMachineApi: 'http://localhost:8080/api/ssr/scheduleStart',
  reservationStopMachineApi: 'http://localhost:8080/api/ssr/scheduleStop',
  reservationRestartMachineApi: 'http://localhost:8080/api/ssr/scheduleRestart',
  errorMassageApi: 'http://localhost:8080/api/ssr/errorMassage'
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
