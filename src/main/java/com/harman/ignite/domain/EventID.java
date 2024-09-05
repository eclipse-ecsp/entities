/*
 * *******************************************************************************
 *
 *  Copyright (c) 2023-24 Harman International
 *
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *
 *  you may not use this file except in compliance with the License.
 *
 *  You may obtain a copy of the License at
 *
 *
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *       
 *
 *  Unless required by applicable law or agreed to in writing, software
 *
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 *  See the License for the specific language governing permissions and
 *
 *  limitations under the License.
 *
 *
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  *******************************************************************************
 */

package com.harman.ignite.domain;

/**
 * Event id.
 */
public class EventID {
    private EventID() {
    }

    public static final String BLOBDATA = "BlobData";
    public static final String ALLTMCEVENTS = "AllTMCEvents";

    public static final String APPSALARM = "AppsAlarm";

    public static final String AUDIOACTIVESOURCE = "AudioActiveSource";

    public static final String AUDIOPLAYSTATECHANGE = "AudioPlayStateChange";

    public static final String AUDIORANDOMMODECHANGE = "AudioRandomModeChange";

    public static final String AUDIOREPEATMODECHANGE = "AudioRepeatModeChange";

    public static final String AUDIOTRACKCHANGE = "AudioTrackChange";

    public static final String AVAILABLEAUDIOSOURCE = "AvailableAudioSource";

    public static final String BTCALLSTATE = "BTCallState";

    public static final String BTCONNECTED = "BTConnected";

    public static final String BTCONTACTSYNCSTATUS = "BTContactSyncStatus";

    public static final String BTDISCONNECTED = "BTDisConnected";

    public static final String BTDISCONNECTED2 = "BTDisconnected";

    public static final String BTHANDSFREEMODE = "BTHandsFreeMode";

    public static final String BTPAIRINGSTATUS = "BTPairingStatus";

    public static final String BTPHONEBATTERYCHARGE = "BTPhoneBatteryCharge";

    public static final String BTPHONENETWORKOPERATOR = "BTPhoneNetworkOperator";

    public static final String BTPHONENETWORKREGSTATE = "BTPhoneNetworkRegState";

    public static final String BTPHONESIGNALQUALITY = "BTPhoneSignalQuality";

    public static final String BTPICTURESYNCSTATUS = "BTPictureSyncStatus";

    public static final String BTSMSSYNCSTATUS = "BTSMSSyncStatus";

    public static final String CPUUSAGE = "CpuUsage";

    public static final String CURRENTTIMEZONE = "CurrentTimeZone";

    public static final String DBOVERLIMIT = "DBOverLimit";

    public static final String DTC = "Dtc";

    public static final String DONGLE_STATUS = "DongleStatus";

    public static final String ECALL = "Ecall";

    public static final String HARDKEYPRESS = "HardKeyPress";

    public static final String HCPCLIENTLAUNCHED = "HCPClientLaunched";

    public static final String HDSTATIONCHANGE = "HDStationChange";

    public static final String HMISTARTUP = "hmiStartup";

    public static final String HUHARDRESET = "HUHardReset";

    public static final String HVACZONE = "HVACZone";

    public static final String IGNSTATUS = "IgnStatus";

    public static final String INTERNETCONNECTED = "InternetConnected";

    public static final String INTERNETDISCONNECTED = "InternetDisconnected";

    public static final String KPI = "KPI";

    public static final String LAUNCHSMSDICTATION = "LaunchSMSDictation";

    public static final String LOCATION = "Location";

    public static final String MEDIADEVICESTATE = "MediaDeviceState";

    public static final String MEMORYUSAGE = "MemoryUsage";

    public static final String MMSOURCE = "MmSource";

    public static final String NADREGSTATE = "NADRegState";

    public static final String APPPLAYBACK = "AppPlayback";

    public static final String NAVDESTINATIONINFO = "NavDestinationInfo";

    public static final String NAVIGATIONROUTECALC = "NavigationRouteCall";

    public static final String NEWSMS = "NewSMS";

    public static final String NOWPLAYING = "NowPlaying";

    public static final String ODOMETER = "Odometer";

    public static final String ONROUTETMCEVENTS = "OnRouteTMCEvents";

    public static final String PHONEBOOKSTATUS = "PhonebookStatus";

    public static final String PIMOBJECTSYNCSTATE = "PIMObjectSyncState";

    public static final String PRNDSTATUS = "Prndstatus";

    public static final String PROCESSDIED = "ProcessDied";

    public static final String REARCAMERASTATUS = "RearCameraStatus";

    public static final String REMOTEOPSSMS = "RemoteOpsSMS";

    public static final String RPM = "RPM";

    public static final String SCREENTRANSITION = "ScreenTransition";

    public static final String SESSIONSTATUS = "SessionStatus";

    public static final String SETTINGSUPDATE = "SettingsUpdate";

    public static final String SPEED = "Speed";

    public static final String DEVICEMESSAGEFAILURE = "DeviceMessageFailure";

    public static final String STATIONCHANGE = "StationChange";

    public static final String SYSTEMSNAPSHOT = "SystemSnapshot";

    public static final String THIRDPARTYAPPSTATUS = "ThirdPartyAppStatus";

    public static final String TOUCHSCREENPRESS = "TouchScreenPress";

    public static final String UPLOADSTATUS = "UploadStatus";

    public static final String VRAVAILABLE = "VrAvailable";

    public static final String VRDIALOGACTIVE = "VrDialogActive";

    public static final String VRMICSTATE = "VrMicState";

    public static final String VRSTATUS = "VrStatus";

    public static final String XMADVISORYMSG = "XMAdvisoryMsg";

    public static final String XMAPPSTATUS = "XMAppStatus";

    public static final String XMSIGNALSTRENGTH = "XMSignalStrength";

    public static final String XMSTATIONCHANGE = "XMStationChange";

    public static final String PHONENETWORKREGSTATE = "BTPhoneNetworkRegState";

    public static final String BTEMAILSYNCSTATUS = "BTEmailSyncStatus";

    public static final String BTCALENDARSYNCSTATUS = "BTCalendarSyncStatus";

    public static final String BTTASKSSYNCSTATUS = "BTTasksSyncStatus";

    public static final String BTNOTESSYNCSTATUS = "BTNotesSyncStatus";

    public static final String BTCALLISTSYNCSTATUS = "BTCalllistSyncStatus";

    // Acknowledgement
    public static final String ACKNOWLEDGEMENT = "Acknowledgement";

    // BMW Specific Events
    public static final String BMW_INTERNAL_CONN_LABEL = "BMW_Internal_Conn_Label";

    public static final String BMW_INTERNAL_LABEL = "BMW_Internal_Label";

    public static final String BMW_INTERNAL_VIN = "BMW_Internal_VIN";
    // Subaru VR Events
    public static final String VRSESSIONSUMMARY = "VrSessionSummary";
    // Porsche drive mode events
    public static final String DRIVEMODE = "DriveMode";

    // ProjectionMode events for Orinoco
    public static final String PROJECTIONMODE = "ProjectionMode";

    public static final String PROJECTIONMODEACTIVITY = "ProjectionModeActivity";

    public static final String DTCSTORED = "DTCStored";
    // Added new eventId called Stability ; this is towards the
    // implementation of HCP-7709 Generic stability event aggregator
    // Start
    public static final String STABILITY = "Stability";

    public static final String DFFEVENT = "DffEvent";

    public static final String GENERICTHIRDPARTYDATAEVENT = "GenericThirdPartyDataEvent";

    public static final String GENERIC_THIRDPARTY_DATA_REQUEST_EVENT = "GenericThirdPartyDataRequestEvent";

    // DFFControlEvent
    public static final String DFF_CONTROL_EVENT = "DFFControlEvent";

    public static final String DEVICE_CONN_STATUS = "DeviceConnStatus";
    
    public static final String FETCH_CONN_STATUS = "FetchConnectionStatus";

    public static final String SERVICE_REMINDER = "ServiceReminder";
    public static final String MAINTENANCE_ALERT = "MaintenanceReminder";
    // Event which store in Stream DLQ topic
    public static final String IGNITE_EXCEPTION_EVENT = "IgniteExceptionEvent";

    public static final String COMPOSITE_EVENT = "CompositeEvent";

    public static final String CREATE_SCHEDULE_EVENT = "CreateScheduleEvent";

    public static final String DELETE_SCHEDULE_EVENT = "DeleteScheduleEvent";

    public static final String SCHEDULE_OP_STATUS_EVENT = "ScheduleOpStatusEvent";

    public static final String SCHEDULE_NOTIFICATION_EVENT = "ScheduleNotificationEvent";

    public static final String DFF_FEEDBACK_EVENT = "DffFeedBackEvent";

    public static final String AMBIENT_AIR_TEMP = "AmbientAirTemp";

    public static final String CAR_BATTERY_VOLTAGE = "CarBatteryVoltage";

    public static final String ENGINE_OIL_LIFE = "EngineOilLife";

    public static final String ENGINE_COOLANT_TEMP = "EngineCoolantTemp";

    public static final String FUEL_ECONOMY = "FuelEconomy";

    public static final String FUEL_CAPACITY = "FuelCapacity";

    public static final String FUEL_LEVEL = "FuelLevel";

    public static final String OEM_VEHICLE_STATUS = "OEMVehicleStatus";

    public static final String LOW_FUEL = "LowFuel";

    public static final String ENGINE_MIL_INDICATOR = "EngineMILindicator";

    public static final String OIL_MAINTENANCE_STATE = "OilMaintenanceState";

    public static final String OIL_PRESSURE = "OilPressure";

    public static final String OUTPUT_SPEED = "OutputSpeed";

    public static final String TYPE_OF_FUEL = "Typeoffuel";

    public static final String TIRE_PRESSURE = "TirePressure";

    public static final String DISTANCE_TO_EMPTY = "DistanceToEmpty";

    public static final String DISTANCE_TO_SERVICE = "DistanceToService";

    public static final String MSG_SEQ_BUF_FLUSH_EVENT = "MsgSeqBufferFlush";

    public static final String ENROLMENT_REQUEST = "EnrolmentRequest";

    public static final String POLICY_REQUEST = "PolicyRequest";

    public static final String POLICY_FEED_REQUEST = "PolicyFeedRequest";

    public static final String VEHICLE_PROFILE_CHANGED_NOTIFICATION_EVENT =
            "VEHICLE_PROFILE_CHANGED_NOTIFICATION_EVENT";

    //Analytics Event for sending data to analytics dashboard via kafka (RTC-301848)
    public static final String DATA_USAGE_EVENT = "DataUsage";
}
