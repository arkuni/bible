package common.constant;

public enum DateConstant {
	JUST("방금")
	,SECONDS_AGO("?초 전")
	,MINUTES_AGO("?분 전")
	,HOURS_AGO("?시간 전")
	,YESTERDAY("어제")
	,DAY_BEFORE_YESTERDAY("그저께")
	,DAYS_AGO("?일 전")
	,ONE_WEEK_AGO("지난 주")
	,WEEKS_AGO("?주 전")
	,ONE_MONTH_AGO("지난 달")
	,MONTHS_AGO("?달 전")
	,YESTERYEAR("작년")
	,TWO_YEARS_AGO("재작년")
	,YEARS_AGO("?년 전")
	,A_LONG_TIME_AGO("아주 오래 전");
	
	private final String description;
	private DateConstant(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
}
