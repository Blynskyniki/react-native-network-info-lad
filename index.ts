import { NativeModules } from 'react-native';

const { RNNetworkInfoLad  } = NativeModules;

/**
 * Описывает возвращаемые значения
 * @interface
 */
export interface IDhcpInfo {
  type:string;
  isConneced:boolean
  gateway: string;
  dns1: string;
  dns2: string;
  ipAddress: string;
  netmask: string;
  serverAddress: string;
}
/**
 * Описывает возвращаемую ошибку
 * @interface
 */
export interface IError {
  errorCode: string;
  errorMsg: string;
}
/**
 * @Class NetworkInfo
 * @Classdesc Статический набор методов
 * для работы с нативным модулем
 */
export class NetworkInfo {
  /**
   * Получить информацию о сети
   * @async
   * @static
   * @return {Promise<IDhcpInfo | IError>}
   */
  public static async  getNetworkInfo():Promise<IDhcpInfo|IError>{
    return await RNNetworkInfoLad.getNetworkInfo();
  }

  /**
   * Проверка является ли шлюз,
   * который передан шлюзом текущей сети,
   * к которой подключен телефон
   * @async
   * @static
   * @param {string} gateway
   * @return {Promise<boolean>}
   */
  public static async  checkGateWay(gateway:string):Promise<boolean>{
    return await RNNetworkInfoLad.checkGateWay(gateway);
  }
}

