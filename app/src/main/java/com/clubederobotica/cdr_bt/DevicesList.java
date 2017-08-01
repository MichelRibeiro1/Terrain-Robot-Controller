package com.clubederobotica.cdr_bt;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

/**
 * Created by michel on 30/07/17.
 */

public class DevicesList extends ListActivity{

    BluetoothAdapter myBtAdapter = null;
    static String MAC_ADDRESS = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> ArrayBT = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        myBtAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> DevicesList= myBtAdapter.getBondedDevices();

        if(DevicesList.size() > 0){
            for(BluetoothDevice dispositvo: DevicesList){
                String nomeBT = dispositvo.getName();
                String macBT = dispositvo.getAddress();
                ArrayBT.add(nomeBT + "\n" + macBT);
            }
        }
        setListAdapter(ArrayBT);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String infoGeral = ((TextView) v).getText().toString();
        String MAC_ADDRESS_local = infoGeral.substring(infoGeral.length()-17);

        Toast.makeText(getApplicationContext(), "MAC: " + MAC_ADDRESS_local, Toast.LENGTH_LONG).show();
        Intent retornaMAC = new Intent();
        retornaMAC.putExtra(MAC_ADDRESS, MAC_ADDRESS_local);
        setResult(RESULT_OK, retornaMAC);
        finish();


    }
}
