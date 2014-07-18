package com.vimukti.dashboard.client.reportdata;

import java.util.ArrayList;
import java.util.List;

import com.vimukti.dashboard.client.data.MetaObject;

@SuppressWarnings("serial")
public class ReportBlockInfo extends MetaObject {

	private List<ReportAggregateReference> aggregateReferences;

	private String blockId;

	private String joinTable;

	public ReportBlockInfo() {
	}

	public List<ReportAggregateReference> getAggregateReferences() {
		return aggregateReferences;
	}

	public void setAggregateReferences(
			List<ReportAggregateReference> aggregateReferences) {
		this.aggregateReferences = aggregateReferences;
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public String getJoinTable() {
		return joinTable;
	}

	public void setJoinTable(String joinTable) {
		this.joinTable = joinTable;
	}
}
