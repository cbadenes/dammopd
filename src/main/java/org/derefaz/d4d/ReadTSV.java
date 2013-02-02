/**********************************************************************
 * 
 * Copyright (c) 2013 Carlos Badenes (cbadenes@gmail.com) .
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 * 
 * @author cbadenes@gmail.com - initial API and implementation
 * @date Jan 28, 2013
 **********************************************************************/
package org.derefaz.d4d;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.derefaz.d4d.antenna.AntennaInfo;
import org.derefaz.d4d.antenna.AntennaPositions;

public class ReadTSV {

    public static void main(String[] args) throws IOException {
        AntennaPositions positions = new AntennaPositions();
        positions.read("/Users/cbadenes/Desktop/antenna_location.arff");

        File file = new File("/Users/cbadenes/Desktop/sample.tsv");
        FileInputStream input = new FileInputStream(file);
        HashMap<String, List<String>> record = new HashMap<String, List<String>>();
        Scanner scanner = new Scanner(input);
        while (scanner.hasNextLine()) {
            StringTokenizer tokenizer = new StringTokenizer(scanner.nextLine());
            String id = tokenizer.nextToken();
            if (Integer.valueOf(id) >= 0) {
                AntennaInfo position = positions.getInfo(id);
                String areaId = position.getAdminArea2();
                List<String> ants = (record.containsKey(areaId)) ? record.get(areaId) : new ArrayList<String>();
                if (!ants.contains(id))
                    ants.add(id);
                record.put(areaId, ants);
            }
        }
        input.close();
        System.out.println("start");
        for (String id : record.keySet()) {
            List<String> ants = record.get(id);

            System.out.println(id + "," + ants.size());
        }
        System.out.println("end");
    }
}
