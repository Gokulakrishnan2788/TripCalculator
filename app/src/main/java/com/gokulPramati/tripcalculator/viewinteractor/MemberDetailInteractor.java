package com.gokulPramati.tripcalculator.viewinteractor;
import com.gokulPramati.tripcalculator.database.DatabaseHelper;
import com.gokulPramati.tripcalculator.listener.MemberDetailListener;
import com.gokulPramati.tripcalculator.model.MemberExpenditures;


/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public class MemberDetailInteractor {
    MemberDetailListener memberDetailListener;
    DatabaseHelper databaseHelper;
    public MemberDetailInteractor(MemberDetailListener tripDetailListener, DatabaseHelper databaseHelper) {
        this.memberDetailListener = tripDetailListener;
        this.databaseHelper = databaseHelper;
    }

    public void addExpenditure(MemberExpenditures memberExpenditures) {
        long insertedId = -1;
        insertedId = databaseHelper.addExpenditure(memberExpenditures);
        if (insertedId > 0) {
            memberExpenditures.setId(insertedId);
            if (memberDetailListener != null) {
                memberDetailListener.onExpenditureAdded(memberExpenditures);
            }
        }

    }

    public void updateMember(MemberExpenditures memberExpenditures) {
        int affectedRows = -1;
        affectedRows = databaseHelper.updateExpenditure(memberExpenditures);
        if (affectedRows > 0) {
            if (memberDetailListener != null) {
                memberDetailListener.onExpenditureUpdated(memberExpenditures);
            }
        }

    }

    public void deleteMember(MemberExpenditures memberExpenditures) {
        databaseHelper.deleteExpenditure(memberExpenditures.getId());
        if (memberDetailListener != null) {
            memberDetailListener.onExpenditureDeleted(memberExpenditures);
        }
    }
}
