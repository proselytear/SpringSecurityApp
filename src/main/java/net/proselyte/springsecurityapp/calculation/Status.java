package net.proselyte.springsecurityapp.calculation;

import java.util.LinkedHashMap;

import net.proselyte.springsecurityapp.var.StaticVarStatus;

public class Status {
	String s_status;
	String s_status_och;
	String s_status_och_group = "-";
	String s_status_och_object = "-";
	String s_usage;
	String s_ready;
	String s_sost;
	String s_alarm;
	String s_cnt;

	String sout_user; // Enable to Manage output
	String scnt_pgm;

	String sZ_alarm;
	String sZ_pass;
	String sZ_st_now;
	String sZ_connect;
	String sZ_battery;
	String sZ_tamper;
	String sZ_status;
	public String zonaAlarmAll = "";// зоны при тревоге
	private String z_state_220 = "";
	private String z_state_battery = "";

	private String sStatusBatteryRu;
	private String sStatusPowerRu;
	LinkedHashMap<Integer, Integer> z_status_datch;
	LinkedHashMap<Integer, Integer> z_status_datch_battery;
	LinkedHashMap<Integer, Integer> z_status_datch_tamper;

	private static boolean is_bit_set(short byte_value, int bit_pos) {
		return (byte_value & (1 << bit_pos)) != 0;
	}

	public Status(String decodeStatusStroka, int numGroup) {
		status_Create(hexStringToByteArray(decodeStatusStroka), numGroup);

	}

	public Status(String decodeStatusStroka) {
		status_Create(hexStringToByteArray(decodeStatusStroka));

	}

	private byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[1 + len / 2];
		data[0] = 1;
		for (int i = 0; i < len; i += 2) {
			byte temp_byte = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
			data[(i / 2) + 1] = temp_byte; // &0xF0;

		}
		return data;
	}

	private void status_Create(byte[] decodeStatusStr) {
		System.out.println("-------------------decodeStatusStr " + decodeStatusStr);
		int numGroup = 0;
		try {
			boolean do_logged = true;
			char i = 0;
			char j = 0;
			byte check_Byte = 0;

			byte status_power = 0;
			byte status_battery = 0;
			byte status_tamper = 0;
			byte status_alarm = 0;
			byte status_sabbotage = 0;
			byte cnt_sirena = 0;
			byte cnt_prog = 0;
			byte[] g_usage = new byte[8];
			byte[] g_ready = new byte[8];
			byte[] g_sost = new byte[8];
			byte[] g_alarm = new byte[8];
			byte[] g_cnt = new byte[8];

			byte[] out_user = new byte[8]; // Enable to Manage output
			byte[] cnt_pgm = new byte[8];

			byte[] z_alarm = new byte[32];
			byte[] z_pass = new byte[32];
			byte[] z_st_now = new byte[32];
			byte[] z_connect = new byte[32]; // нет связи с датчиком
			byte[] z_battery = new byte[32];// разряд батареи датчика
			byte[] z_tamper = new byte[32];// датчик вскрвт
			byte[] z_status = new byte[32];// принадлежность группе

			int num_datch = 0;
			int num_datch_battery = 0;
			int num_datch_tamper = 0;

			z_state_220 = "";
			z_state_battery = "";

			if (decodeStatusStr[0] == 1) { // 0-Protocol info
				// 1-GSM info
				if (decodeStatusStr[1] < 0x80) {
				} else {
					check_Byte = decodeStatusStr[1];
					if (is_bit_set(check_Byte, 6) == false && is_bit_set(check_Byte, 5) == true) {
					} else // Bad signal
					if (is_bit_set(check_Byte, 6) == true && is_bit_set(check_Byte, 5) == false) {
					} else // Good Signal
					if (is_bit_set(check_Byte, 6) == true && is_bit_set(check_Byte, 5) == true) {
					} else {
					}
				}
				// 2-WiFi info
				if (decodeStatusStr[2] < 0x80) {
				} else {
					check_Byte = decodeStatusStr[2];
					if (is_bit_set(check_Byte, 6) == false && is_bit_set(check_Byte, 5) == true) {
					} else // ST
					if (is_bit_set(check_Byte, 6) == true && is_bit_set(check_Byte, 5) == false) {
					} else // AP
					if (is_bit_set(check_Byte, 6) == true && is_bit_set(check_Byte, 5) == true) {
					} else {
					}
				}
				// 3-Power info
				if (decodeStatusStr[3] < 0x80)
					status_power = 0;
				else {
					status_power = 1;// нет 220
					z_state_220 = "Нет напряжения 220В";
					System.out.println("Нет напряжения 220В");
				}
				status_battery = (byte) (decodeStatusStr[3] & 0x3F);
				if ((int) status_battery == 1) {
					z_state_battery = "Аккумулятор разряжен";
					System.out.println("Аккумулятор разряжен");
				}
				// status_battery=1 аккумулятор разрядился
				// 4-Perifirals
				if (is_bit_set(decodeStatusStr[4], 7) == true)
					status_tamper = 1;
				else
					status_tamper = 0;
				if (is_bit_set(decodeStatusStr[4], 5) == true)
					cnt_sirena = 1;
				else
					cnt_sirena = 0;
				if (is_bit_set(decodeStatusStr[4], 4) == true)
					cnt_prog = 1;
				else
					cnt_prog = 0;
				if (is_bit_set(decodeStatusStr[4], 3) == true)
					status_alarm = 1;
				else
					status_alarm = 0;
				if (is_bit_set(decodeStatusStr[4], 1) == true)
					status_sabbotage = 1;
				else
					status_sabbotage = 0;
				// TODO: 6-Tamper 2, 2-keboard commun, 0 - Other status
				// 5-Reserved
				// Groups and Zones
				int g_sost_count_ochr = 0;
				int g_sost_count_notochr = 0;
				int g_alarm_full = 0;

				z_status_datch = new LinkedHashMap<Integer, Integer>();
				z_status_datch_battery = new LinkedHashMap<Integer, Integer>();
				z_status_datch_tamper = new LinkedHashMap<Integer, Integer>();

				for (i = 0; i < 8; i++) {
					if (is_bit_set(decodeStatusStr[5], i) == true)
						g_usage[i] = 1;
					else
						g_usage[i] = 0;
					if (is_bit_set(decodeStatusStr[6], i) == true)
						g_ready[i] = 1;
					else
						g_ready[i] = 0;
					if (is_bit_set(decodeStatusStr[7], i) == true)
						g_sost[i] = 1;
					else
						g_sost[i] = 0; // status ohrany 1-yes, 0-no
					if (is_bit_set(decodeStatusStr[8], i) == true) // тревого
						g_alarm[i] = 1;
					else
						g_alarm[i] = 0;
					if (is_bit_set(decodeStatusStr[9], i) == true)
						g_cnt[i] = 1;
					else
						g_cnt[i] = 0;
					if (g_usage[i] > 0) {
						if (g_sost[i] == 0) {
							g_sost_count_notochr += 1;
						} else {
							g_sost_count_ochr += 1;
						}
						if (g_alarm[i] == 1)
							g_alarm_full = 1;
					}

					if (g_sost[i] == 1) {
						System.out.println(i);
						s_status_och_group = StaticVarStatus.statusGroupOchrana;
						if (s_status_och_object.equals("-"))
							s_status_och_object = StaticVarStatus.statusGroupOchrana;

						if (!s_status_och_object.equals(StaticVarStatus.statusGroupAlarm)
								&& s_status_och_object.equals(StaticVarStatus.statusGroupWithoutOchrana))
							s_status_och_object = StaticVarStatus.statusGroupOchranaPatrial;
						else if (!s_status_och_object.equals(StaticVarStatus.statusGroupAlarm)
								&& s_status_och_object.equals(StaticVarStatus.statusGroupOchrana))
							s_status_och_object = StaticVarStatus.statusGroupOchrana;
					} else {
						if (s_status_och_object.equals("-"))
							s_status_och_object = StaticVarStatus.statusGroupWithoutOchrana;
						s_status_och_group = StaticVarStatus.statusGroupWithoutOchrana;
						if (!s_status_och_object.equals(StaticVarStatus.statusGroupAlarm)
								&& s_status_och_object.equals(StaticVarStatus.statusGroupWithoutOchrana))
							s_status_och_object = StaticVarStatus.statusGroupWithoutOchrana;
						else if (!s_status_och_object.equals(StaticVarStatus.statusGroupAlarm)
								&& s_status_och_object.equals(StaticVarStatus.statusGroupOchrana))
							s_status_och_object = StaticVarStatus.statusGroupOchranaPatrial;
					}
					if (g_alarm[i] == 1) {
						s_status_och_group = StaticVarStatus.statusGroupAlarm;
						s_status_och_object = StaticVarStatus.statusGroupAlarm;
					}

					if (is_bit_set(decodeStatusStr[10], i) == true)
						out_user[i] = 1;
					else
						out_user[i] = 0;
					if (is_bit_set(decodeStatusStr[11], i) == true)
						cnt_pgm[i] = 1;
					else
						cnt_pgm[i] = 0;

					if (is_bit_set(decodeStatusStr[12], i) == true) {
						z_alarm[i] = 1;// зона при тревоге
						zonaAlarmAll += (i + 1) + ",";
					} else
						z_alarm[i] = 0;
					if (is_bit_set(decodeStatusStr[13], i + 8) == true)
						z_alarm[i + 8] = 1;
					else
						z_alarm[i + 8] = 0;
					if (is_bit_set(decodeStatusStr[14], i + 16) == true)
						z_alarm[i + 16] = 1;
					else
						z_alarm[i + 16] = 0;
					if (is_bit_set(decodeStatusStr[15], i + 24) == true)
						z_alarm[i + 24] = 1;
					else
						z_alarm[i + 24] = 0;

					if (is_bit_set(decodeStatusStr[16], i) == true)
						z_pass[i] = 1;
					else
						z_pass[i] = 0;
					if (is_bit_set(decodeStatusStr[7], i + 8) == true)
						z_pass[i + 8] = 1;
					else
						z_pass[i + 8] = 0;
					if (is_bit_set(decodeStatusStr[18], i + 16) == true)
						z_pass[i + 16] = 1;
					else
						z_pass[i + 16] = 0;
					if (is_bit_set(decodeStatusStr[19], i + 24) == true)
						z_pass[i + 24] = 1;
					else
						z_pass[i + 24] = 0;

					if (is_bit_set(decodeStatusStr[20], i) == true)
						z_st_now[i] = 1;
					else
						z_st_now[i] = 0;
					if (is_bit_set(decodeStatusStr[21], i + 8) == true)
						z_st_now[i + 8] = 1;
					else
						z_st_now[i + 8] = 0;
					if (is_bit_set(decodeStatusStr[22], i + 16) == true)
						z_st_now[i + 16] = 1;
					else
						z_st_now[i + 16] = 0;
					if (is_bit_set(decodeStatusStr[23], i + 24) == true)
						z_st_now[i + 24] = 1;
					else
						z_st_now[i + 24] = 0;

					if (is_bit_set(decodeStatusStr[24], i) == true) {
						z_connect[i] = 1;
						num_datch = i;
					} else
						z_connect[i] = 0;
					if (is_bit_set(decodeStatusStr[25], i + 8) == true) {
						z_connect[i + 8] = 1;
						num_datch = i + 8;
					} else
						z_connect[i + 8] = 0;
					if (is_bit_set(decodeStatusStr[26], i + 16) == true) {
						z_connect[i + 16] = 1;
						num_datch = i + 16;
					} else
						z_connect[i + 16] = 0;
					if (is_bit_set(decodeStatusStr[27], i + 24) == true) {
						z_connect[i + 24] = 1;
						num_datch = i + 24;
					} else
						z_connect[i + 24] = 0;

					if (is_bit_set(decodeStatusStr[28], i) == true) {
						z_battery[i] = 1;
						num_datch_battery = i;
					} else
						z_battery[i] = 0;
					if (is_bit_set(decodeStatusStr[29], i + 8) == true) {
						z_battery[i + 8] = 1;

					} else
						z_battery[i + 8] = 0;
					if (is_bit_set(decodeStatusStr[30], i + 16) == true) {
						z_battery[i + 16] = 1;

					} else
						z_battery[i + 16] = 0;
					if (is_bit_set(decodeStatusStr[31], i + 24) == true) {
						z_battery[i + 24] = 1;

					} else
						z_battery[i + 24] = 0;

					if (is_bit_set(decodeStatusStr[32], i) == true) {
						z_tamper[i] = 1;

					} else
						z_tamper[i] = 0;
					if (is_bit_set(decodeStatusStr[33], i + 8) == true) {
						z_tamper[i + 8] = 1;

					} else
						z_tamper[i + 8] = 0;
					if (is_bit_set(decodeStatusStr[34], i + 16) == true) {
						z_tamper[i + 16] = 1;

					} else
						z_tamper[i + 16] = 0;
					if (is_bit_set(decodeStatusStr[35], i + 24) == true) {
						z_tamper[i + 24] = 1;

					} else
						z_tamper[i + 24] = 0;

					j = (char) decodeStatusStr[36 + i];
					check_Byte = (byte) (j >> 4);
					z_status[2 * i] = (byte) (check_Byte & 0x0F);
					z_status[2 * i + 1] = (byte) (j & 0x0F);
					j = (char) decodeStatusStr[44 + i];
					check_Byte = (byte) (j >> 4);
					z_status[2 * i + 16] = (byte) (check_Byte & 0x0F);
					z_status[2 * i + 17] = (byte) (j & 0x0F);

				}
				num_datch = 0;
				for (byte i_conn : z_connect) {

					if ((int) i_conn == 1) {
						z_status_datch.put(num_datch, (int) z_status[num_datch]);
					}

					num_datch += 1;
				}
				num_datch_battery = 0;
				for (byte i_battery : z_battery) {

					if ((int) i_battery == 1) {
						z_status_datch.put(num_datch_battery, (int) z_status[num_datch_battery]);
					}

					num_datch_battery += 1;
				}
				num_datch_tamper = 0;
				for (byte i_tamper : z_tamper) {
					if ((int) i_tamper == 1) {
						z_status_datch.put(num_datch_tamper, (int) z_status[num_datch_tamper]);
					}

					num_datch_battery += 1;

				}
				if (g_sost_count_ochr == 0 && g_sost_count_notochr == 0) {
					s_status_och = "-";
				} else if (g_sost_count_ochr > 0 && g_sost_count_notochr == 0) {
					s_status_och = "ПОД ОХРАНОЙ";
				} else if (g_sost_count_ochr == 0 && g_sost_count_notochr > 1) {
					s_status_och = "БЕЗ ОХРАНЫ";
				} else {
					s_status_och = "ЧАСТИЧНАЯ ОХРАНА";
				}
				if (g_alarm_full == 0) {
					s_status_och = "ТРЕВОГА";
				}

				if (do_logged == true) {

					/*
					 * if (gsm_in_use==0) s_status="GSM disable"; else { s_status="GSM enable; ";
					 * s_status+="; GSM Signal="+Integer.toString(gsm_signal);
					 * s_status+="; GSM Process="+Integer.toString(gsm_etap); }
					 * System.out.println(s_status); if (wifi_in_use==0) s_status="WIFI disable";
					 * else { s_status="WIFI enable; ";
					 * s_status+="; WIFI Mode="+Integer.toString(wifi_mode);
					 * s_status+="; WIFI Process="+Integer.toString(wifi_etap); }
					 * System.out.println(s_status);
					 */
					s_status = "Perifiral Status: ";
					if (status_power == 0) {
						s_status += "POW OK; ";
						sStatusPowerRu = StaticVarStatus.statusNorma;
					} else {
						s_status += "POW Trouble; ";
						sStatusPowerRu = StaticVarStatus.statusCrash;
					}
					if (status_battery == 0) {
						s_status += "BAT OK; ";
						sStatusBatteryRu = StaticVarStatus.statusNorma;
					} else {
						s_status += "BAT Trouble; ";
						sStatusBatteryRu = StaticVarStatus.statusCrash;
					}
					if (status_tamper == 0)
						s_status += "TAMPER OK; ";
					else
						s_status += "TAMPER Trouble; ";
					if (status_alarm == 0)
						s_status += "ALARM OK; ";
					else
						s_status += "ALARM Trouble; ";
					if (status_sabbotage == 0)
						s_status += "NO SABBOTAGE; ";
					else
						s_status += "ALARM SABBOTAGE; ";

					if (cnt_sirena == 0)
						s_status += "SIREN OFF; ";
					else
						s_status += "SIREN ON; ";
					if (cnt_prog > 0)
						s_status += "PROGRAMMING MODE; ";

					for (i = 0; i < 32; i++) {
						if (i < 8) {
							if (g_usage[i] > 0)
								s_usage += "GR." + Integer.toString(i + 1) + "-YES; ";
							else
								s_usage += "GR." + Integer.toString(i + 1) + "-NO; ";
							if (g_ready[i] > 0)
								s_ready += "GR." + Integer.toString(i + 1) + "-NO; ";
							else
								s_ready += "GR." + Integer.toString(i + 1) + "-YES; ";
							if (g_sost[i] > 0)
								s_sost += "GR." + Integer.toString(i + 1) + "-CLOSE; ";
							else
								s_sost += "GR." + Integer.toString(i + 1) + "-OPEN; ";
							if (g_alarm[i] > 0)
								s_alarm += "GR." + Integer.toString(i + 1) + "-ALARM; ";
							else
								s_alarm += "GR." + Integer.toString(i + 1) + "-NO; ";
							if (g_cnt[i] > 0)
								s_cnt += "GR." + Integer.toString(i + 1) + "-YES; ";
							else
								s_cnt += "GR." + Integer.toString(i + 1) + "-NO; ";

							if (out_user[i] > 0)
								sout_user += "PGM." + Integer.toString(i + 1) + "-YES; ";
							else
								sout_user += "PGM." + Integer.toString(i + 1) + "-NO; ";
							if (cnt_pgm[i] > 0)
								scnt_pgm += "PGM." + Integer.toString(i + 1) + "-YES; ";
							else
								scnt_pgm += "PGM." + Integer.toString(i + 1) + "-NO; ";
						}

						if (z_status[i] > 0)
							sZ_status += "ZN." + Integer.toString(i + 1) + "-gr." + Integer.toString(z_status[i]) + ";";
						else
							sZ_status += "ZN." + Integer.toString(i + 1) + "-disable;";
						if (z_st_now[i] == 0)
							sZ_st_now += "ZN." + Integer.toString(i + 1) + "-OK;";
						else
							sZ_st_now += "ZN." + Integer.toString(i + 1) + "-ER;";
						if (z_pass[i] > 0)
							sZ_pass += "ZN." + Integer.toString(i + 1) + "-YES;";
						else
							sZ_pass += "ZN." + Integer.toString(i + 1) + "-NO;";
						if (z_alarm[i] > 0)
							sZ_alarm += "ZN." + Integer.toString(i + 1) + "-YES;";
						else
							sZ_alarm += "ZN." + Integer.toString(i + 1) + "-NO;";
						if (z_connect[i] > 0)
							sZ_connect += "ZN." + Integer.toString(i + 1) + "-YES;";
						else
							sZ_connect += "ZN." + Integer.toString(i + 1) + "-NO;";
						if (z_battery[i] > 0)
							sZ_battery += "ZN." + Integer.toString(i + 1) + "-YES; ";
						else
							sZ_battery += "ZN." + Integer.toString(i + 1) + "-NO; ";
						if (z_tamper[i] > 0)
							sZ_tamper += "ZN." + Integer.toString(i + 1) + "-YES; ";
						else
							sZ_tamper += "ZN." + Integer.toString(i + 1) + "-NO; ";

					}

				}

			} // End - Protocol Valid

		} catch (

		Exception e) {

			System.out.println("Create Status crashed!");
		}

	}

	private void status_Create(byte[] decodeStatusStr, int numGroup) {
		try {
			boolean do_logged = true;
			char i = 0;
			char j = 0;
			byte check_Byte = 0;

			byte status_power = 0;
			byte status_battery = 0;
			byte status_tamper = 0;
			byte status_alarm = 0;
			byte status_sabbotage = 0;
			byte cnt_sirena = 0;
			byte cnt_prog = 0;
			byte[] g_usage = new byte[8];
			byte[] g_ready = new byte[8];
			byte[] g_sost = new byte[8];
			byte[] g_alarm = new byte[8];
			byte[] g_cnt = new byte[8];

			byte[] out_user = new byte[8]; // Enable to Manage output
			byte[] cnt_pgm = new byte[8];

			byte[] z_alarm = new byte[32];
			byte[] z_pass = new byte[32];
			byte[] z_st_now = new byte[32];
			byte[] z_connect = new byte[32]; // нет связи с датчиком
			byte[] z_battery = new byte[32];// разряд батареи датчика
			byte[] z_tamper = new byte[32];// датчик вскрвт
			byte[] z_status = new byte[32];// принадлежность группе

			int num_datch = 0;
			int num_datch_battery = 0;
			int num_datch_tamper = 0;

			z_state_220 = "";
			z_state_battery = "";

			if (decodeStatusStr[0] == 1) { // 0-Protocol info
				// 1-GSM info
				if (decodeStatusStr[1] < 0x80) {
				} else {
					check_Byte = decodeStatusStr[1];
					if (is_bit_set(check_Byte, 6) == false && is_bit_set(check_Byte, 5) == true) {
					} else // Bad signal
					if (is_bit_set(check_Byte, 6) == true && is_bit_set(check_Byte, 5) == false) {
					} else // Good Signal
					if (is_bit_set(check_Byte, 6) == true && is_bit_set(check_Byte, 5) == true) {
					} else {
					}
				}
				// 2-WiFi info
				if (decodeStatusStr[2] < 0x80) {
				} else {
					check_Byte = decodeStatusStr[2];
					if (is_bit_set(check_Byte, 6) == false && is_bit_set(check_Byte, 5) == true) {
					} else // ST
					if (is_bit_set(check_Byte, 6) == true && is_bit_set(check_Byte, 5) == false) {
					} else // AP
					if (is_bit_set(check_Byte, 6) == true && is_bit_set(check_Byte, 5) == true) {
					} else {
					}
				}
				// 3-Power info
				if (decodeStatusStr[3] < 0x80)
					status_power = 0;
				else {
					status_power = 1;// нет 220
					z_state_220 = "Нет напряжения 220В";
					System.out.println("Нет напряжения 220В");
				}
				status_battery = (byte) (decodeStatusStr[3] & 0x3F);
				if ((int) status_battery == 1) {
					z_state_battery = "Аккумулятор разряжен";
					System.out.println("Аккумулятор разряжен");
				}
				// status_battery=1 аккумулятор разрядился
				// 4-Perifirals
				if (is_bit_set(decodeStatusStr[4], 7) == true)
					status_tamper = 1;
				else
					status_tamper = 0;
				if (is_bit_set(decodeStatusStr[4], 5) == true)
					cnt_sirena = 1;
				else
					cnt_sirena = 0;
				if (is_bit_set(decodeStatusStr[4], 4) == true)
					cnt_prog = 1;
				else
					cnt_prog = 0;
				if (is_bit_set(decodeStatusStr[4], 3) == true)
					status_alarm = 1;
				else
					status_alarm = 0;
				if (is_bit_set(decodeStatusStr[4], 1) == true)
					status_sabbotage = 1;
				else
					status_sabbotage = 0;
				// TODO: 6-Tamper 2, 2-keboard commun, 0 - Other status
				// 5-Reserved
				// Groups and Zones
				int g_sost_count_ochr = 0;
				int g_sost_count_notochr = 0;
				int g_alarm_full = 0;

				z_status_datch = new LinkedHashMap<Integer, Integer>();
				z_status_datch_battery = new LinkedHashMap<Integer, Integer>();
				z_status_datch_tamper = new LinkedHashMap<Integer, Integer>();

				for (i = 0; i < 8; i++) {
					if (is_bit_set(decodeStatusStr[5], i) == true)
						g_usage[i] = 1;
					else
						g_usage[i] = 0;
					if (is_bit_set(decodeStatusStr[6], i) == true)
						g_ready[i] = 1;
					else
						g_ready[i] = 0;
					if (is_bit_set(decodeStatusStr[7], i) == true)
						g_sost[i] = 1;
					else
						g_sost[i] = 0; // status ohrany 1-yes, 0-no
					if (is_bit_set(decodeStatusStr[8], i) == true) // тревого
						g_alarm[i] = 1;
					else
						g_alarm[i] = 0;
					if (is_bit_set(decodeStatusStr[9], i) == true)
						g_cnt[i] = 1;
					else
						g_cnt[i] = 0;
					if (g_usage[i] > 0) {
						if (g_sost[i] == 0) {
							g_sost_count_notochr += 1;
						} else {
							g_sost_count_ochr += 1;
						}
						if (g_alarm[i] == 1)
							g_alarm_full = 1;
					}
					if (numGroup - 1 == i) {
						if (g_sost[i] == 1) {
							s_status_och_group = StaticVarStatus.statusGroupOchrana;

						} else {
							s_status_och_group = StaticVarStatus.statusGroupWithoutOchrana;

						}
						if (g_alarm[i] == 1) {
							s_status_och_group = StaticVarStatus.statusGroupAlarm;

						}
					}

					if (is_bit_set(decodeStatusStr[10], i) == true)
						out_user[i] = 1;
					else
						out_user[i] = 0;
					if (is_bit_set(decodeStatusStr[11], i) == true)
						cnt_pgm[i] = 1;
					else
						cnt_pgm[i] = 0;

					if (is_bit_set(decodeStatusStr[12], i) == true) {
						z_alarm[i] = 1;// зона при тревоге
						zonaAlarmAll += (i + 1) + ",";
					} else
						z_alarm[i] = 0;
					if (is_bit_set(decodeStatusStr[13], i + 8) == true)
						z_alarm[i + 8] = 1;
					else
						z_alarm[i + 8] = 0;
					if (is_bit_set(decodeStatusStr[14], i + 16) == true)
						z_alarm[i + 16] = 1;
					else
						z_alarm[i + 16] = 0;
					if (is_bit_set(decodeStatusStr[15], i + 24) == true)
						z_alarm[i + 24] = 1;
					else
						z_alarm[i + 24] = 0;

					if (is_bit_set(decodeStatusStr[16], i) == true)
						z_pass[i] = 1;
					else
						z_pass[i] = 0;
					if (is_bit_set(decodeStatusStr[7], i + 8) == true)
						z_pass[i + 8] = 1;
					else
						z_pass[i + 8] = 0;
					if (is_bit_set(decodeStatusStr[18], i + 16) == true)
						z_pass[i + 16] = 1;
					else
						z_pass[i + 16] = 0;
					if (is_bit_set(decodeStatusStr[19], i + 24) == true)
						z_pass[i + 24] = 1;
					else
						z_pass[i + 24] = 0;

					if (is_bit_set(decodeStatusStr[20], i) == true)
						z_st_now[i] = 1;
					else
						z_st_now[i] = 0;
					if (is_bit_set(decodeStatusStr[21], i + 8) == true)
						z_st_now[i + 8] = 1;
					else
						z_st_now[i + 8] = 0;
					if (is_bit_set(decodeStatusStr[22], i + 16) == true)
						z_st_now[i + 16] = 1;
					else
						z_st_now[i + 16] = 0;
					if (is_bit_set(decodeStatusStr[23], i + 24) == true)
						z_st_now[i + 24] = 1;
					else
						z_st_now[i + 24] = 0;

					if (is_bit_set(decodeStatusStr[24], i) == true) {
						z_connect[i] = 1;
						num_datch = i;
					} else
						z_connect[i] = 0;
					if (is_bit_set(decodeStatusStr[25], i + 8) == true) {
						z_connect[i + 8] = 1;
						num_datch = i + 8;
					} else
						z_connect[i + 8] = 0;
					if (is_bit_set(decodeStatusStr[26], i + 16) == true) {
						z_connect[i + 16] = 1;
						num_datch = i + 16;
					} else
						z_connect[i + 16] = 0;
					if (is_bit_set(decodeStatusStr[27], i + 24) == true) {
						z_connect[i + 24] = 1;
						num_datch = i + 24;
					} else
						z_connect[i + 24] = 0;

					if (is_bit_set(decodeStatusStr[28], i) == true) {
						z_battery[i] = 1;
						num_datch_battery = i;
					} else
						z_battery[i] = 0;
					if (is_bit_set(decodeStatusStr[29], i + 8) == true) {
						z_battery[i + 8] = 1;

					} else
						z_battery[i + 8] = 0;
					if (is_bit_set(decodeStatusStr[30], i + 16) == true) {
						z_battery[i + 16] = 1;

					} else
						z_battery[i + 16] = 0;
					if (is_bit_set(decodeStatusStr[31], i + 24) == true) {
						z_battery[i + 24] = 1;

					} else
						z_battery[i + 24] = 0;

					if (is_bit_set(decodeStatusStr[32], i) == true) {
						z_tamper[i] = 1;

					} else
						z_tamper[i] = 0;
					if (is_bit_set(decodeStatusStr[33], i + 8) == true) {
						z_tamper[i + 8] = 1;

					} else
						z_tamper[i + 8] = 0;
					if (is_bit_set(decodeStatusStr[34], i + 16) == true) {
						z_tamper[i + 16] = 1;

					} else
						z_tamper[i + 16] = 0;
					if (is_bit_set(decodeStatusStr[35], i + 24) == true) {
						z_tamper[i + 24] = 1;

					} else
						z_tamper[i + 24] = 0;

					j = (char) decodeStatusStr[36 + i];
					check_Byte = (byte) (j >> 4);
					z_status[2 * i] = (byte) (check_Byte & 0x0F);
					z_status[2 * i + 1] = (byte) (j & 0x0F);
					j = (char) decodeStatusStr[44 + i];
					check_Byte = (byte) (j >> 4);
					z_status[2 * i + 16] = (byte) (check_Byte & 0x0F);
					z_status[2 * i + 17] = (byte) (j & 0x0F);

				}
				num_datch = 0;
				for (byte i_conn : z_connect) {

					if ((int) i_conn == 1) {
						z_status_datch.put(num_datch, (int) z_status[num_datch]);
					}

					num_datch += 1;
				}
				num_datch_battery = 0;
				for (byte i_battery : z_battery) {

					if ((int) i_battery == 1) {
						z_status_datch.put(num_datch_battery, (int) z_status[num_datch_battery]);
					}

					num_datch_battery += 1;
				}
				num_datch_tamper = 0;
				for (byte i_tamper : z_tamper) {
					if ((int) i_tamper == 1) {
						z_status_datch.put(num_datch_tamper, (int) z_status[num_datch_tamper]);
					}

					num_datch_battery += 1;

				}
				if (g_sost_count_ochr == 0 && g_sost_count_notochr == 0) {
					s_status_och = "-";
				} else if (g_sost_count_ochr > 0 && g_sost_count_notochr == 0) {
					s_status_och = "ПОД ОХРАНОЙ";
				} else if (g_sost_count_ochr == 0 && g_sost_count_notochr > 1) {
					s_status_och = "БЕЗ ОХРАНЫ";
				} else {
					s_status_och = "ЧАСТИЧНАЯ ОХРАНА";
				}
				if (g_alarm_full == 0) {
					s_status_och = "ТРЕВОГА";
				}

				if (do_logged == true) {

					/*
					 * if (gsm_in_use==0) s_status="GSM disable"; else { s_status="GSM enable; ";
					 * s_status+="; GSM Signal="+Integer.toString(gsm_signal);
					 * s_status+="; GSM Process="+Integer.toString(gsm_etap); }
					 * System.out.println(s_status); if (wifi_in_use==0) s_status="WIFI disable";
					 * else { s_status="WIFI enable; ";
					 * s_status+="; WIFI Mode="+Integer.toString(wifi_mode);
					 * s_status+="; WIFI Process="+Integer.toString(wifi_etap); }
					 * System.out.println(s_status);
					 */
					s_status = "Perifiral Status: ";
					if (status_power == 0) {
						s_status += "POW OK; ";
						sStatusPowerRu = StaticVarStatus.statusNorma;
					} else {
						s_status += "POW Trouble; ";
						sStatusPowerRu = StaticVarStatus.statusCrash;
					}
					if (status_battery == 0) {
						s_status += "BAT OK; ";
						sStatusBatteryRu = StaticVarStatus.statusNorma;
					} else {
						s_status += "BAT Trouble; ";
						sStatusBatteryRu = StaticVarStatus.statusCrash;
					}
					if (status_tamper == 0)
						s_status += "TAMPER OK; ";
					else
						s_status += "TAMPER Trouble; ";
					if (status_alarm == 0)
						s_status += "ALARM OK; ";
					else
						s_status += "ALARM Trouble; ";
					if (status_sabbotage == 0)
						s_status += "NO SABBOTAGE; ";
					else
						s_status += "ALARM SABBOTAGE; ";

					if (cnt_sirena == 0)
						s_status += "SIREN OFF; ";
					else
						s_status += "SIREN ON; ";
					if (cnt_prog > 0)
						s_status += "PROGRAMMING MODE; ";

					for (i = 0; i < 32; i++) {
						if (i < 8) {
							if (g_usage[i] > 0)
								s_usage += "GR." + Integer.toString(i + 1) + "-YES; ";
							else
								s_usage += "GR." + Integer.toString(i + 1) + "-NO; ";
							if (g_ready[i] > 0)
								s_ready += "GR." + Integer.toString(i + 1) + "-NO; ";
							else
								s_ready += "GR." + Integer.toString(i + 1) + "-YES; ";
							if (g_sost[i] > 0)
								s_sost += "GR." + Integer.toString(i + 1) + "-CLOSE; ";
							else
								s_sost += "GR." + Integer.toString(i + 1) + "-OPEN; ";
							if (g_alarm[i] > 0)
								s_alarm += "GR." + Integer.toString(i + 1) + "-ALARM; ";
							else
								s_alarm += "GR." + Integer.toString(i + 1) + "-NO; ";
							if (g_cnt[i] > 0)
								s_cnt += "GR." + Integer.toString(i + 1) + "-YES; ";
							else
								s_cnt += "GR." + Integer.toString(i + 1) + "-NO; ";

							if (out_user[i] > 0)
								sout_user += "PGM." + Integer.toString(i + 1) + "-YES; ";
							else
								sout_user += "PGM." + Integer.toString(i + 1) + "-NO; ";
							if (cnt_pgm[i] > 0)
								scnt_pgm += "PGM." + Integer.toString(i + 1) + "-YES; ";
							else
								scnt_pgm += "PGM." + Integer.toString(i + 1) + "-NO; ";
						}

						if (z_status[i] > 0)
							sZ_status += "ZN." + Integer.toString(i + 1) + "-gr." + Integer.toString(z_status[i]) + ";";
						else
							sZ_status += "ZN." + Integer.toString(i + 1) + "-disable;";
						if (z_st_now[i] == 0)
							sZ_st_now += "ZN." + Integer.toString(i + 1) + "-OK;";
						else
							sZ_st_now += "ZN." + Integer.toString(i + 1) + "-ER;";
						if (z_pass[i] > 0)
							sZ_pass += "ZN." + Integer.toString(i + 1) + "-YES;";
						else
							sZ_pass += "ZN." + Integer.toString(i + 1) + "-NO;";
						if (z_alarm[i] > 0)
							sZ_alarm += "ZN." + Integer.toString(i + 1) + "-YES;";
						else
							sZ_alarm += "ZN." + Integer.toString(i + 1) + "-NO;";
						if (z_connect[i] > 0)
							sZ_connect += "ZN." + Integer.toString(i + 1) + "-YES;";
						else
							sZ_connect += "ZN." + Integer.toString(i + 1) + "-NO;";
						if (z_battery[i] > 0)
							sZ_battery += "ZN." + Integer.toString(i + 1) + "-YES; ";
						else
							sZ_battery += "ZN." + Integer.toString(i + 1) + "-NO; ";
						if (z_tamper[i] > 0)
							sZ_tamper += "ZN." + Integer.toString(i + 1) + "-YES; ";
						else
							sZ_tamper += "ZN." + Integer.toString(i + 1) + "-NO; ";

					}

				}

			} // End - Protocol Valid

		} catch (

		Exception e) {

			System.out.println("Create Status crashed!");
		}

	}

	public String getS_status() {
		return s_status;
	}

	public String getS_usage() {
		return s_usage;
	}

	public String getS_ready() {
		return s_ready;
	}

	public String getS_sost() {
		return s_sost;
	}

	public String getS_alarm() {
		return s_alarm;
	}

	public String getS_cnt() {
		return s_cnt;
	}

	public String getSout_user() {
		return sout_user;
	}

	public String getScnt_pgm() {
		return scnt_pgm;
	}

	public String getsZ_alarm() {
		return sZ_alarm;
	}

	public String getsZ_pass() {
		return sZ_pass;
	}

	public String getsZ_st_now() {
		return sZ_st_now;
	}

	public String getsZ_connect() {
		return sZ_connect;
	}

	public String getsZ_battery() {
		return sZ_battery;
	}

	public String getsZ_tamper() {
		return sZ_tamper;
	}

	public String getsZ_status() {
		return sZ_status;
	}

	public String getS_status_och() {
		return s_status_och;
	}

	public String getS_status_och_group() {
		return s_status_och_group;
	}

	public String getsStatusBatteryRu() {
		return sStatusBatteryRu;
	}

	public void setsStatusBatteryRu(String sStatusBatteryRu) {
		this.sStatusBatteryRu = sStatusBatteryRu;
	}

	public String getsStatusPowerRu() {
		return sStatusPowerRu;
	}

	public void setsStatusPowerRu(String sStatusPower) {
		this.sStatusPowerRu = sStatusPower;
	}

	public LinkedHashMap<Integer, Integer> getZ_status_datch() {
		return z_status_datch;
	}

	public void setZ_status_datch(LinkedHashMap<Integer, Integer> z_status_datch) {
		this.z_status_datch = z_status_datch;
	}

	public LinkedHashMap<Integer, Integer> getZ_status_datch_battery() {
		return z_status_datch_battery;
	}

	public void setZ_status_datch_battery(LinkedHashMap<Integer, Integer> z_status_datch_battery) {
		this.z_status_datch_battery = z_status_datch_battery;
	}

	public LinkedHashMap<Integer, Integer> getZ_status_datch_tamper() {
		return z_status_datch_tamper;
	}

	public void setZ_status_datch_tamper(LinkedHashMap<Integer, Integer> z_status_datch_tamper) {
		this.z_status_datch_tamper = z_status_datch_tamper;
	}

	public String getZ_state_220() {
		return z_state_220;
	}

	public void setZ_state_220(String z_state_220) {
		this.z_state_220 = z_state_220;
	}

	public String getZ_state_battery() {
		return z_state_battery;
	}

	public void setZ_state_battery(String z_state_battery) {
		this.z_state_battery = z_state_battery;
	}

	public String getS_status_och_object() {
		return s_status_och_object;
	}

	public void setS_status_och_object(String s_status_och_object) {
		this.s_status_och_object = s_status_och_object;
	}

}
